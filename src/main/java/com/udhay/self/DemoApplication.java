package com.udhay.self;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@GetMapping("/webhooks")
	public int validateRequest(@RequestParam("hub.challenge") int challenge, @RequestParam("hub.mode") String mode, @RequestParam("hub.verify_token") String token) {
		
		System.out.println("challenge "+challenge+" mode "+mode +" token "+token);
		
		return challenge;
	}

}
