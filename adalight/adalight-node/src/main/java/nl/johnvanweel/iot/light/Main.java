package nl.johnvanweel.iot.light;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class Main {
    /**
     * Spring application context
     */
    private static ApplicationContext appCtx;

    public static final void main(String... args) throws InterruptedException {
        List<String> configs = new LinkedList<>();
        configs.add("classpath:/META-INF/spring/lights-main-context.xml");

        appCtx = new ClassPathXmlApplicationContext(configs.toArray(new String[configs.size()]));

        while (true) {
            Thread.sleep(5000);
        }
    }
}
