package pl.kamsoft.nfz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pl.kamsoft.nfz.parser.UserParser;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping
	public void sayHello() {
		final String uri = "http://${server.address}:8080/get/users";

		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

	    if (HttpStatus.OK == response.getStatusCode()) {
	        System.out.println(response.toString());
	        UserParser.getUsers(response.getBody());
	    } else {
	        System.out.println("error");
	    }
	}
}
