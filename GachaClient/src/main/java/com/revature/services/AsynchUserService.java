package com.revature.services;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.revature.beans.HistoricalCat;

import reactor.core.publisher.Flux;

@Service
public class AsynchUserService {
	public void printAllGachas() {
		WebClient webClient = WebClient.create();
		Flux<HistoricalCat> cats = webClient.get()
				.uri("http://localhost:8080/gachas")
				.accept(MediaType.APPLICATION_NDJSON)
				.retrieve()
				.bodyToFlux(HistoricalCat.class);
		cats.subscribe((item) -> {
			System.out.println(item);
		});

		int i = 0;
		while(true) {
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
