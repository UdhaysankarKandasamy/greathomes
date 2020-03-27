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
		System.out.println("version 2.1... ");
		SpringApplication.run(GreatHomes.class, args);
	}
	
	@GetMapping("/webhooks")
	public int validateRequest(@RequestParam("hub.challenge") int challenge, @RequestParam("hub.mode") String mode, @RequestParam("hub.verify_token") String token) {
		
		System.out.println("challenge "+challenge+" mode "+mode +" token "+token);
		
		return challenge;
	}
	
	@PostMapping("/webhooks")
	public HttpStatus eventNotification(@RequestBody Request request) {
		System.out.println("Request.. {}"+request);
		if(null==request)
			System.out.println("Request object is null and couldn't receive anything... ");
		System.out.println("Response -> field-> "+request.getField()+ "object "+request.getObject());
		
		if(null!=request.getValue()) {
			System.out.println(" media "+request.getValue().getMedia_id()+" comment "+request.getValue().getComment_id());
		}
		if(null!=request.getEntry()&& !request.getEntry().isEmpty()) {
			System.out.println("size "+request.getEntry().size());
			System.out.println("id "+request.getEntry().get(0).getId()+" time "+request.getEntry().get(0).getTime()+" uid "+request.getEntry().get(0).getUid());
			if(null!=request.getEntry().get(0).getChanges() && !request.getEntry().get(0).getChanges().isEmpty()) {
				System.out.println("changes "+request.getEntry().get(0).getChanges().get(0).getField());
			}
			
		}
		
		return HttpStatus.OK;
	}

}
