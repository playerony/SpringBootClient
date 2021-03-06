package pl.kamsoft.nfz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pl.kamsoft.nfz.exception.RestClientException;
import pl.kamsoft.nfz.parser.UserParser;
import pl.kamsoft.nfz.utility.GetPropertyValues;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private GetPropertyValues getPropertyValues;
	
	@GetMapping
	public void sayHello() throws RestClientException {
		final String uri = "http://" + getPropertyValues.get("server.address") + "/get/users";

		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

	    if (HttpStatus.OK == response.getStatusCode()) {
	        System.out.println(response.toString());
	        UserParser.getUsers(response.getBody());
	    } else {
	    	throw new RestClientException("HttpStatus is not OK in sayHello method");
	    }
	}
}
