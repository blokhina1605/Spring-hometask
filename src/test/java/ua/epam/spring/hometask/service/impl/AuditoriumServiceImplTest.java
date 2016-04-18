package ua.epam.spring.hometask.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * @author Yevheniia_Blokhina.
 */
public class AuditoriumServiceImplTest {

    private AuditoriumServiceImpl auditoriumService;

    private ConfigurableApplicationContext ctx;

    @Before
    public void setUp() {
        ctx = new ClassPathXmlApplicationContext("spring.xml");
        auditoriumService = ctx.getBean(AuditoriumServiceImpl.class);
    }

    @Test
    public void testGetAll(){

    }
}