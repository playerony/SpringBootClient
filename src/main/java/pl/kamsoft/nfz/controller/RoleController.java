package pl.kamsoft.nfz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import pl.kamsoft.nfz.exception.RestClientException;
import pl.kamsoft.nfz.parser.AuthorParser;
import pl.kamsoft.nfz.parser.RoleParser;
import pl.kamsoft.nfz.utility.GetPropertyValues;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private GetPropertyValues getPropertyValues;
	
	@GetMapping("/all")
	public String index(Model model) throws RestClientException {
		final String uri = "http://" + getPropertyValues.get("server.address") + "/get/roles";

		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

	    if (HttpStatus.OK == response.getStatusCode()) {
	        model.addAttribute("roles", RoleParser.getRoles(response.getBody()));
	        
	        AuthorParser.getAuthors(response.getBody()).forEach(e -> System.out.println(e.getFirstName()));
	    } else {
	    	throw new RestClientException("HttpStatus is not OK in index method");
	    }
		
		return "role";
	}
}
