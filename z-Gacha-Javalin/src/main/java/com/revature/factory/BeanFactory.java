package com.revature.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BeanFactory {
	private static Logger log = LogManager.getLogger(BeanFactory.class);
	private static BeanFactory bf = null;
	
	private BeanFactory() {}
	
	public static synchronized BeanFactory getFactory() {
		if(bf == null) {
			bf = new BeanFactory();
		}
		return bf;
	}
 	
	public Object get(Class<?> inter, Class<?> clazz) {
		if(!clazz.isAnnotationPresent(Log.class)) {
			throw new RuntimeException("Class not annotated with @Log");
		}
		Object o = null;
		Constructor<?> c;
		try {
			c = clazz.getConstructor(); // implicitly requiring a no-args constructor
			
			// Creating a Proxy of the INTERFACE implemented by the CLASS
			o = Proxy.newProxyInstance(inter.getClassLoader(),
					new Class[] {inter},
					new LogProxy(c.newInstance()));
			
		} catch(Exception e) {
			log.error("Method threw exception: "+e);
			for(StackTraceElement s: e.getStackTrace()) {
				log.warn(s);
			}
			throw new BeanCreationFailureException(e); // choosing to fail but I'm wrapping the exception
			// in a runtime exception so I don't have to handle it.
			// This means that the failure is such that it shouldn't be handled and 
			// should result in the failure of the application.
		}
		
		return o;
	}
}

class BeanCreationFailureException extends RuntimeException {
	private static final long serialVersionUID = -4665442177863738341L;

	public BeanCreationFailureException(Exception e) {
		super(e);
	}
}
