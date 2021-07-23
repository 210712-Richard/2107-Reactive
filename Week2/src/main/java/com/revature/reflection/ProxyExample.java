package com.revature.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyExample {
	public static void main(String[] args) {
		SpreadFactory sf = new SpreadFactory();
		Spreadable spread = sf.getSpreadable();
		spread.spread();
	}
}

class MyProxy implements InvocationHandler {
	Object obj;
	
	public MyProxy(Object o) {
		obj = o;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// Itself,		method being called, arguments to that method
		// log the method and arguments
		Object result;
		System.out.println("Invoking "+method+" with args: "+Arrays.toString(args));
		try{
			result = (args==null) ? method.invoke(obj) : method.invoke(obj, args);
		} catch(Exception e) {
			// log the exception
			throw e;
		}
		System.out.println("Method returns: "+result);
		return result;
	}	
}

class SpreadFactory{
	public Spreadable getSpreadable() {
		// create an object that wraps around my Butter Object with the My Proxy object
		// The compiler and the JVM will treat JUST LIKE a regular Butter object but the MyProxy
		// stuff will happen as well.
		return (Spreadable) Proxy.newProxyInstance(Spreadable.class.getClassLoader(),
				new Class[] {Spreadable.class},
				new MyProxy(new Butter()));
	}
}

interface Spreadable {
	void spread();
}

class Butter implements Spreadable {
	@Override
	public void spread() {
		System.out.println("Butter reporting for duty");
	}
}