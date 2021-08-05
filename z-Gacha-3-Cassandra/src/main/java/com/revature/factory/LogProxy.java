package com.revature.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogProxy implements InvocationHandler {
	private Logger log;
	private Object obj;
	
	public LogProxy(Object o) {
		obj = o;
		log = LogManager.getLogger(o.getClass());
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		try {
			if(args == null) {
				log.trace("Method "+method+" called with no arguments");
				result = method.invoke(obj);
			} else {
				log.trace("Method "+method+" called with arguments "+Arrays.toString(args));
				result = method.invoke(obj, args);
			}
			log.trace("Method returned: "+ result);
		} catch(Exception e) {
			log.error("Method threw exception: "+e);
			for(StackTraceElement s: e.getStackTrace()) {
				log.warn(s);
			}
			if(e.getCause() != null) {
				Throwable t = e.getCause();
				log.error("Method threw wrapped exception: "+t);
				for(StackTraceElement s: t.getStackTrace()) {
					log.warn(s);
				}
			}
			throw e; // if the proxy doesn't throw the exception again, we'll have changed
			// the code to never throw exceptions, which sometimes is good but might be bad.
		}
		
		return result;
	}

}
