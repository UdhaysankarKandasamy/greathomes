package com.udhay.self;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GreatHomes {

	public static void main(String[] args) {
		System.out.println("version 2.0... ");
		SpringApplication.run(GreatHomes.class, args);
	}
	
	@GetMapping("/webhooks")
	public int validateRequest(@RequestParam("hub.challenge") int challenge, @RequestParam("hub.mode") String mode, @RequestParam("hub.verify_token") String token) {
		
		System.out.println("challenge "+challenge+" mode "+mode +" token "+token);
		
		return challenge;
	}
	
	@PostMapping("/webhooks")
	public HttpStatus eventNotification(@RequestBody Request request) {
		if(null==request)
			System.out.println("Request object is null and couldn't receive anything... ");
		System.out.println("Response -> field-> "+request.getField());
		
		if(null!=request.getValue()) {
			System.out.println(" media "+request.getValue().getMedia_id()+" comment "+request.getValue().getComment_id());
		}
		
		return HttpStatus.OK;
	}

}
