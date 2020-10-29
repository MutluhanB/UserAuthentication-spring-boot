package com.example.authdemo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        System.out.println("created app context");
        CONTEXT = context;
    }

    public static Object getBean(String beanName){
        System.out.println(CONTEXT);
        return CONTEXT.getBean(beanName);
    }
}
