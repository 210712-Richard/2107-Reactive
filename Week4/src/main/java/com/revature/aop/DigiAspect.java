package com.revature.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DigiAspect {

	// @Before("digiHook()") // checks the pointcut to find joinpoints and then runs
	// before those joinpoints
	public void digiHello() {
		System.out.println("This advice says hello!");
	}

	// @After("digiHook()")
	public void digiGoodbye() {
		System.out.println("This advice says goodbye!");
	}

	//@AfterReturning(pointcut="digiHook()", returning="obj")
	public void digiGoodbye(Object obj) {
		System.out.println("This advice lets us see what got returned: "+obj);
		if(obj instanceof Digimon) {
			// calling a method inside of an advice can cause that advice to be reapplied
			((Digimon) obj).setName("Numemon");
			// stack overflow below
			//((Digimon) obj).digivolve("SkullGreymon");
		}
	}
	
	//@AfterThrowing(pointcut="digiHook()", throwing="t")
	public void throwingObject(Throwable t) {
		System.out.println("Only if thrown: "+t);
	}
	
	@Around("digiHook()")
	public Object digiEverything(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("Before the method call");
		Object o = null;
		
		System.out.println(Arrays.toString(pjp.getArgs()));
		// with great digipower comes great digiresponsibility
		
		// 1. must actually call the method
		// 2. ACTUALLY RETURN THE RESULT
		// 3. If an exception occurs we should rethrow it
		try {
			o =  pjp.proceed();
		} catch (Exception e) {
			throw e;
		}
		
		System.out.println("After the method call: "+o);
		System.out.println(o==null?null:o.getClass());
		if("Waste".equals(o)) {
			//o = new Digimon();
			if(((Digimon) pjp.getThis()).getName().equals("Agumon")) {
				o = "Agumon cake";
			} else {
				o = "Delicious Cake";
			}
		}
		return o;
	}

	// Hook: An empty method that serves to hold the metadata of an annotation
	@Pointcut("execution( * com.revature.aop.Digimon.*(..) )")
	public void digiHook() {
		/* Empty method for a hook */ }
}
