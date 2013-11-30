package nl.johnvanweel.iot.light.runmode;

import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.IlluminationGroupMessage;
import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.RunmodeGroupMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.function.Predicate;

/**
 * Basic controller for the runmodes. Keeps track of stopping/starting.
 */
public class RunModeControl {
    private final Logger logger = Logger.getLogger(RunModeControl.class);

    private final List<RunMode> modes;

    private RunMode currentMode;
    private RunMode defaultMode;

    private Thread currentModeThread;

    @Autowired
    public RunModeControl(final List<RunMode> modes) {
        this.modes = modes;
    }

    @PostConstruct
    public void start() {
        start(defaultMode);
    }

    /**
     * Finds the node contained in the message and starts it. If no matches found, it will start the default.
     *
     * @param groupMessage message
     */
    public void start(final RunmodeGroupMessage groupMessage) {
        start(
                modes.parallelStream()
                        .filter(matchMode(groupMessage))
                        .findAny()
                        .orElse(defaultMode)
        );
    }

    /**
     * Reconfigure the currently running mode.
     *
     * @param message message
     */
    public void reconfigure(IlluminationGroupMessage message) {
        currentMode.reconfigure(message);
    }

    private Predicate<? super RunMode> matchMode(RunmodeGroupMessage groupMessage) {
        return e -> e.identify().equalsIgnoreCase(groupMessage.getMode());
    }

    private void start(RunMode mode) {
        stopCurrentMode();
        startNewMode(mode);
    }

    private void startNewMode(final RunMode mode) {
        currentMode = mode;
        currentMode.start();

        currentModeThread = new Thread(currentMode);
        currentModeThread.start();
    }

    private void stopCurrentMode() {
        if (currentModeThread != null) {
            currentMode.cleanStop();
            try {
                currentModeThread.join();
            } catch (InterruptedException e) {
                logger.warn(e);
            }
        }
    }

    public RunMode getDefaultMode() {
        return defaultMode;
    }

    public void setDefaultMode(RunMode defaultMode) {
        this.defaultMode = defaultMode;
    }
}
