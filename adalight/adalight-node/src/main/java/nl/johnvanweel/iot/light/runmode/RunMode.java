package nl.johnvanweel.iot.light.runmode;

import org.apache.log4j.Logger;


/**
 * RunMode for the light system. Provides the basic features of Threading.
 */
public abstract class RunMode implements Runnable {
    private final Logger logger = Logger.getLogger(RunMode.class);

    private boolean keepRunning = true;
    private int speed;

    /**
     * Prepare a clean stop of the Thread.
     */
    public void cleanStop() {
        this.keepRunning = false;
    }

    /**
     * Initializes the runmode
     */
    public void start() {
        this.keepRunning = true;
    }

    @Override
    public void run() {
        while (keepRunning) {
            executeStep();

            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                logger.error(e);
                ;
            }
        }
    }

    /**
     * Execute a single light-change step.
     */
    protected abstract void executeStep();

    /**
     * Identifies the runmode
     *
     * @return the identification
     */
    public abstract String identify();

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isKeepRunning() {
        return keepRunning;
    }
}
