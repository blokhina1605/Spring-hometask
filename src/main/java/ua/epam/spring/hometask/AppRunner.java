package ua.epam.spring.hometask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Yevheniia_Blokhina.
 */

@Component
public class AppRunner {

    @Autowired
    private Demo demo;

    public void setDemo(Demo demo) {
        this.demo = demo;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring-annotation.xml");
        AppRunner appRunner = ctx.getBean(AppRunner.class);

        appRunner.demo.run();
        ctx.close();
    }
}
