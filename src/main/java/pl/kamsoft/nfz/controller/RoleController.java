package pl.kamsoft.nfz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import pl.kamsoft.nfz.parser.AuthorParser;
import pl.kamsoft.nfz.parser.RoleParser;

@Controller
@RequestMapping("/role")
public class RoleController {
	@GetMapping("/all")
	public String index(Model model) {
		final String uri = "http://${server.address}:8080/get/roles";

		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

	    if (HttpStatus.OK == response.getStatusCode()) {
	        System.out.println(response.toString());
	        model.addAttribute("roles", RoleParser.getRoles(response.getBody()));
	        
	        AuthorParser.getAuthors(response.getBody()).forEach(e -> System.out.println(e.getFirstName()));
	    } else {
	        System.out.println("error");
	    }
		
		return "role";
	}
}
