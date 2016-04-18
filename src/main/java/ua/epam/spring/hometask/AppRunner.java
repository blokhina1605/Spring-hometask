package ua.epam.spring.hometask;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Yevheniia_Blokhina.
 */
public class AppRunner {

    private App app;

    public void setApp(App app) {
        this.app = app;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        AppRunner appRunner = ctx.getBean(AppRunner.class);
//        appRunner.app.run();
        ctx.close();
    }
}
