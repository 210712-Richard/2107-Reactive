package com.revature.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class Bean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean{
	private String name;
	private Player player;
	//@Autowired
	private Ball ball;
	
	public Bean() {
		System.out.println("Phase One: Creation");
		System.out.println("1. Instantiation: No args constructor");
	}
	
	@Autowired
	public Bean(Ball b) {
		System.out.println("Phase One: Creation");
		System.out.println("1. Instantiation: Constructor Injection");
		ball = b;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Player getPlayer() {
		return player;
	}

	@Autowired
	public void setPlayer(Player player) {
		System.out.println("2. Properties Set: Setter Injection: Player");
		this.player = player;
	}

	public Ball getBall() {
		return ball;
	}

	//@Autowired
	public void setBall(Ball ball) {
		System.out.println("2. Properties Set: Setter Injection: Ball");
		this.ball = ball;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// What created me if we're being fancy?

		System.out.println("5. ApplicationContext aware "+ applicationContext);
		
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// What created me?

		System.out.println("4. BeanFactory Aware "+ beanFactory);
		
	}

	@Override
	public void setBeanName(String name) {
		// What is my name?
		System.out.println("Phase Two: Awareness");
		System.out.println("3. Name Aware: "+ name);
		this.name = name;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// What do we need to do before we can use this bean?
		System.out.println("8. afterPropertiesSet");
		// ex. initialize a connection to a db, read a file, read environment variables, etc.
	}
	
	@PostConstruct
	public void aMethod() {
		// what do we need to do before we can use this bean?
		System.out.println("7. postConstruct (alternative to afterPropertiesSet)");
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("12. destroy from DisposableBean");
	}
	
	@PreDestroy
	public void customDestroy() {
		System.out.println("Phase Five: Destruction");
		System.out.println("11. PreDestroy, custom cleanup");
	}
	
}
