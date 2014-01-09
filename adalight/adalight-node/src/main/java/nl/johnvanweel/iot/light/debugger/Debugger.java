package nl.johnvanweel.iot.light.debugger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class Debugger {
    /**
     * Spring application context
     */
    private static ApplicationContext appCtx;

    public static final void main(String... args) throws InterruptedException {
        List<String> configs = new LinkedList<>();
        configs.add("classpath:/META-INF/spring/cluster-access.xml");
        configs.add("classpath:/META-INF/spring/lights.xml");
        configs.add("classpath:/META-INF/spring/plumbing.xml");
        configs.add("classpath:/META-INF/spring/debugger.xml");

        appCtx = new ClassPathXmlApplicationContext(configs.toArray(new String[configs.size()]));

        while (true) {
            Thread.sleep(5000);
        }
    }
}
