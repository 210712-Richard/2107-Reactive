package com.revature;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class TestController {
	@GetMapping(value = "/hello", produces=MediaType.TEXT_HTML_VALUE)
	public Flux<String> hello(){
		Flux<String> helloFlux = Flux.<String>generate(sink -> sink.next("Hello\n")); // creates an infinite flux of the String Hello

		return helloFlux.take(Duration.ofSeconds(5));
	}
	
	@GetMapping(value = "/bean", produces=MediaType.APPLICATION_NDJSON_VALUE)
	public Flux<Bean> bean(){
		Flux<Bean> helloFlux = Flux.<Bean>generate(sink -> sink.next(new Bean("Hello")));

		return helloFlux.delayElements(Duration.ofSeconds(1)).take(Duration.ofSeconds(5));
	}
	
	@GetMapping(value="/hi", produces=MediaType.APPLICATION_NDJSON_VALUE)
	public ResponseEntity<Flux<Bean>> hi() {
		List<Bean> beans = new ArrayList<Bean>();
		beans.add(new Bean("Hello"));
		beans.add(new Bean("Richard"));
		beans.add(new Bean("Nice Hat"));
		beans.add(new Bean("Stylish"));
		beans.add(new Bean("Goodbye"));
		Flux<Bean> fluxBeans = Flux.fromStream(beans.stream()); // we can create a Flux of any Java Stream
		fluxBeans = fluxBeans.map((b)-> {
			return new Bean(b.getName()+" transformed.");
		}).delayElements(Duration.ofSeconds(1));
		
		return ResponseEntity.ok(fluxBeans);
	}
	
	@GetMapping(value="/anotherFlux", produces=MediaType.APPLICATION_NDJSON_VALUE)
	public ResponseEntity<Flux<Bean>> another() {
		Flux<Bean> fluxBeans = Flux.just(new Bean("one"), new Bean("two"), new Bean("three"), new Bean("four"));
		fluxBeans = fluxBeans.map((b)-> {
			return new Bean(b.getName()+" transformed.");
		}).delayElements(Duration.ofSeconds(1));
		
		return ResponseEntity.ok(fluxBeans);
	}
}