package com.ong;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHelper implements ApplicationContextAware{
	private static ApplicationContext context = null;  
	  
    public void setApplicationContext(ApplicationContext applicationContext)  
            throws BeansException {  
        context = applicationContext;  
    }  
      
    public static Object getBean(String name){  
        return context.getBean(name);  
    } 
    
    public static <T> T getBean(Class<T> clazz){  
        return context.getBean(clazz);  
    }
}
