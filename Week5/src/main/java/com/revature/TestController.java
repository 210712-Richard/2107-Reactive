package com.revature;

import java.time.Duration;

import org.springframework.http.MediaType;
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
}