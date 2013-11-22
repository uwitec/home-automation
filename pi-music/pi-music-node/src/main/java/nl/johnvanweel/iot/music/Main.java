package nl.johnvanweel.iot.music;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.LinkedList;
import java.util.List;

/**
 * Main application, starts the Spring context
 */
public class Main {
    /**
     * Spring application context
     */
    private static ApplicationContext appCtx;

    public static final void main(String... args) {
        List<String> configs = new LinkedList<>();
        configs.add("classpath:/META-INF/spring/context.xml");

        appCtx = new ClassPathXmlApplicationContext(configs.toArray(new String[configs.size()]));
    }
}
