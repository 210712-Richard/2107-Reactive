package com.revature.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.revature.aop")
@EnableAspectJAutoProxy // enable Spring AOP
public class AppConfig {
	
}
