package nl.johnvanweel.iot.light.runmode;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

	@PreDestroy
	public void preDestroy(){
		currentModeThread.interrupt();
	}

    /**
     * Finds the node contained in the message and starts it. If no matches found, it will start the default.
     *
     * @param mode message
     */
    public void start(final String mode) {
        start(
                modes.parallelStream()
                        .filter(matchMode(mode))
                        .findAny()
                        .orElse(defaultMode)
        );
    }

	public void start(RunMode mode) {
		stopCurrentMode();
		startNewMode(mode);
	}

    /**
     * Reconfigure the currently running mode.
     *
	 * @param message message
	 */
    public void reconfigure(int[] message) {
        currentMode.reconfigure(message);
    }

    private Predicate<? super RunMode> matchMode(String mode) {
        return e -> e.identify().equalsIgnoreCase(mode);
    }

    private void startNewMode(final RunMode mode) {
		logger.info("Starting with new mode " + mode);
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
