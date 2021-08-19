//package com.revature.aspects;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.revature.beans.User;
//import com.revature.beans.UserType;
//
//import io.javalin.http.Context;
//
//@Component
//@Aspect
//@Order(Ordered.LOWEST_PRECEDENCE)
//public class AuthenticationAspect {
//	
//	private Logger log = LogManager.getLogger(AuthenticationAspect.class);
//	
//	// Handler methods are void
//	@Around("loggedInHook()")
//	public void checkLoggedIn(ProceedingJoinPoint pjp) throws Throwable {
//		log.debug("CHECKING LOG IN");
//		Context ctx = null;
//		// We know this method should only be applied to Handler methods,
//		// so there should be *exactly* 1 argument, and that argument should
//		// be a Context object.
//		ctx = (Context) pjp.getArgs()[0];
//		
//		User loggedUser = ctx.sessionAttribute("loggedUser");
//		// Checking if logged in
//		if (loggedUser == null) {
//			ctx.status(401);
//			return;
//		} else {
//			pjp.proceed(); // We are logged in. Call the method.
//		}
//	}
//	@Around("correctUserHook()")
//	public void checkCorrectUser(ProceedingJoinPoint pjp) throws Throwable {
//		log.debug("CHECKING USER");
//		Context ctx = null;
//		ctx = (Context) pjp.getArgs()[0];
//		
//		User loggedUser = ctx.sessionAttribute("loggedUser");
//		String username = ctx.pathParam("username");
//		// Checking if logged in
//		if (loggedUser == null) {
//			ctx.status(401);
//			return;
//		}
//		
//		if (username != null && username.equals(loggedUser.getUsername())) {
//			// We are logged in as the correct user
//			pjp.proceed();
//		} else {
//			ctx.status(403);
//		}
//	}
//	
//	@Around("gameMasterHook()")
//	public void checkAdmin(ProceedingJoinPoint pjp) throws Throwable {
//		log.debug("CHECKING GAMEMASTER");
//		Context ctx = null;
//		ctx = (Context) pjp.getArgs()[0];
//		
//		User loggedUser = ctx.sessionAttribute("loggedUser");
//		// Checking if logged in
//		if (loggedUser == null) {
//			ctx.status(401);
//			return;
//		}
//		
//		if (UserType.GAME_MASTER.equals(loggedUser.getType())) {
//			// We are logged in as the correct user
//			pjp.proceed();
//		} else {
//			ctx.status(403);
//		}
//	}
//
//	@Pointcut("@annotation(com.revature.aspects.LoggedIn)")
//	private void loggedInHook() {/*Empty Method For Hook*/}
//	
//	@Pointcut("@annotation(com.revature.aspects.CorrectUser)")
//	private void correctUserHook() {/*Empty Method For Hook*/}
//	
//	@Pointcut("@annotation(com.revature.aspects.GameMaster)")
//	private void gameMasterHook() {/*Empty Method For Hook*/}
//	//@Pointcut("execution( * com.revature..*(Context ctx) )")
//}
