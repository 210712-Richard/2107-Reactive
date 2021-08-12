package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class Driver {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Driver.class); // we don't want to start a web server
		application.setWebApplicationType(WebApplicationType.NONE);
		
		retrieveMyBeans();
	}

	private static void retrieveMyBeans() {
		WebClient client= WebClient.create("http://localhost:8080");
		
		client.get().uri("/hi").accept(MediaType.APPLICATION_NDJSON).retrieve().bodyToFlux(Bean.class).subscribe(bean->{
			System.out.println(bean);
		});
		
		while(true) {
			int i = 0;
			System.out.println("hello");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
			if(i>10) {
				break;
			}
		}
	}
}
