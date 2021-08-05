package com.revature.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProcessorBean implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// All beans will be subject to processing
		if ("bean".equals(beanName)) {
			System.out.println("Phase Three: Initialization");
			System.out.println("6. Pre-initialization");
			// Do we need to mutate our bean before we can use it?
		}
		// after we've processed the bean we need to return it or we break everything.
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// All beans will be subject to processing
		if ("bean".equals(beanName)) {
			System.out.println("9. Post-initialization");
			// Do we need to clean up the bean after we initalized it before we use it?
		}
		// after we've processed the bean we need to return it or we break everything.
		return bean;
	}

}
