package pl.kamsoft.nfz.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.playerony.libraryV2.model.Author;

import pl.kamsoft.nfz.exception.RestClientException;
import pl.kamsoft.nfz.parser.AuthorParser;
import pl.kamsoft.nfz.utility.GetPropertyValues;

@Controller
@RequestMapping("/author")
public class AuthorController {
	@Autowired
	private GetPropertyValues getPropertyValues;
	
	@GetMapping("/all")
	public String index(Model model) throws RestClientException {
		final String url = "http://" + getPropertyValues.get("server.address") + "/get/authors";

		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

	    if (HttpStatus.OK == response.getStatusCode()) {
	        model.addAttribute("authors", AuthorParser.getAuthors(response.getBody()));
	        
	        AuthorParser.getAuthors(response.getBody()).forEach(e -> System.out.println(e.getFirstName()));
	    } else {
	        throw new RestClientException("HttpStatus is not OK in index method");
	    }
		
		return "author";
	}
	
	@GetMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("author", new Author());
		
		return "add/addAuthor";
	}
	
	@PostMapping("/addAuthor")
	public String addAuthor(@Valid Author author, BindingResult bindingResult) throws RestClientException {
		String url = "http://" + getPropertyValues.get("server.address") + "/post/addAuthor";
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, author, Author.class);
		
		return "redirect:/author/all";
	}
	
	@PostMapping(value = "/removeAuthor")
	public String remove(@RequestParam(value="deleteButton") Long id) {
		String url = "http://" + getPropertyValues.get("server.address") + "/post/deleteAuthor/";
		new RestTemplate().put(url + id, id);
		
		return "redirect:/author/all";
	}
	
	@GetMapping(value = "/edit")
	public String edit(@RequestParam(value="editButton") Long id, Model model) throws RestClientException {
		model.addAttribute("author", getAuthorByID(id));
		
		return "edit/editAuthor";
	}
	
	private Author getAuthorByID(Long id) throws RestClientException {
		String url = "http://" + getPropertyValues.get("server.address") + "/get/author/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

	    if (HttpStatus.OK == response.getStatusCode()) {
	    	Author author = restTemplate.getForObject(url, Author.class);
	    	
	    	return author;
	    } else {
	        throw new RestClientException("HttpStatus is not OK in getAuthorByID method");
	    }
	}
}
