package nl.johnvanweel.iot.light.demo;

import nl.johnvanweel.iot.light.service.ILightService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * This component will select a random demo and run it.
 */
public class DemoController {
    private Logger logger = Logger.getLogger(DemoController.class);

    private final ILightService ILightService;

    private final IDemo[] demos;

    @Autowired
    public DemoController(final ILightService ILightService, IDemo... demos) {
        this.ILightService = ILightService;
        this.demos = demos;
    }

    @PostConstruct
    public void postConstruct() throws InterruptedException {
        Random random = new Random();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int selected = random.nextInt(demos.length);
                    logger.info("Playing demo " + demos[selected].getClass());
                    try {
                        demos[selected].runRemo();
                    } catch (InterruptedException e) {
                        logger.warn("Aborted.", e);
                    }
                }
            }
        }).start();
    }
}
