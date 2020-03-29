package com.udhay.self;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.udhay.self.domain.Domain;

@SpringBootApplication
@RestController
public class GreatHomes {

	@Autowired
	Domain domain;

	public static void main(String[] args) {
		System.out.println("version 2.1... ");
		SpringApplication.run(GreatHomes.class, args);
	}

	@GetMapping("/webhooks")
	public int validateRequest(@RequestParam("hub.challenge") int challenge, @RequestParam("hub.mode") String mode,
			@RequestParam("hub.verify_token") String token) {

		System.out.println("challenge " + challenge + " mode " + mode + " token " + token);

		return challenge;
	}

	@PostMapping("/webhooks")
	public HttpStatus eventNotification(@RequestBody Request request) {
		Gson gson = new Gson();
		System.out.println("Request.. {}" + gson.toJson(request));
		if (null == request)
			System.out.println("Request object is null and couldn't receive anything... ");
		return HttpStatus.OK;
	}

	@GetMapping("/start")
	public String startAction() {
		domain.startSeriesActions();
		return "commented";
	}
	
	@GetMapping("/site")
	public String getSiteContent(@RequestParam("sku") String sku) {
		return "Here is your site URL "+sku;
	}

}
