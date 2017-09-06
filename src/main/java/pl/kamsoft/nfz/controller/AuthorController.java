package pl.kamsoft.nfz.controller;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.playerony.libraryV2.model.Author;

import pl.kamsoft.nfz.parser.AuthorParser;
import pl.kamsoft.nfz.parser.UserParser;
import pl.kamsoft.nfz.utility.GetPropertyValues;

@Controller
@RequestMapping("/author")
public class AuthorController {
	@Autowired
	private GetPropertyValues getPropertyValues;
	
	@GetMapping("/all")
	public String index(Model model) throws IOException {
		final String uri = "http://" + getPropertyValues.get("server.address") + "/get/authors";

		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

	    if (HttpStatus.OK == response.getStatusCode()) {
	        System.out.println(response.toString());
	        model.addAttribute("authors", AuthorParser.getAuthors(response.getBody()));
	        
	        AuthorParser.getAuthors(response.getBody()).forEach(e -> System.out.println(e.getFirstName()));
	    } else {
	        System.out.println("error");
	    }
		
		return "author";
	}
	
	@GetMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("author", new Author());
		
		return "add/addAuthor";
	}
	
	@PostMapping("/addAuthor")
	public String addAuthor(@Valid Author author, BindingResult bindingResult) throws IOException {
		String url = "http://" + getPropertyValues.get("server.address") + "/post/addAuthor";
		
		System.out.println(author.toString());
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, author, Author.class);
		
		return "redirect:/author/all";
	}
	
	@PostMapping(value = "/removeAuthor")
	public String remove(@RequestParam(value="deleteButton") Long id) throws IOException {
		String url = "http://" + getPropertyValues.get("server.address") + "/post/deleteAuthor/";

		new RestTemplate().put(url + id, id);
		
		return "redirect:/author/all";
	}
	
	@GetMapping(value = "/edit")
	public String edit(@RequestParam(value="editButton") Long id, Model model) throws IOException {
		model.addAttribute("author", getAuthorByID(id));
		return "edit/editAuthor";
	}
	
	@PostMapping(value = "/editAuthor")
	public String editBook(@Valid Author author, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) 
			return "edit/editAuthor";
		
		//authorService.updateAuthor(author.getId(), author);
		return "redirect:/author/all";
	}
	
	private Author getAuthorByID(Long id) throws IOException {
		String url = "http://" + getPropertyValues.get("server.address") + "/get/author/";

		//Map<String, String> params = new HashMap<String, String>();
		//params.put("id", id.toString());

		RestTemplate restTemplate = new RestTemplate();
		Author author = restTemplate.getForObject(url + id, Author.class);
		System.out.println(author.toString());
		
		return author;
	}
}
