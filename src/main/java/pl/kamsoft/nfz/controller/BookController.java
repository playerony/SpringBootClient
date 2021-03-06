package pl.kamsoft.nfz.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import com.playerony.libraryV2.model.Author;
import com.playerony.libraryV2.model.Book;

import pl.kamsoft.nfz.exception.RestClientException;
import pl.kamsoft.nfz.parser.BookParser;
import pl.kamsoft.nfz.utility.GetPropertyValues;


@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	private GetPropertyValues getPropertyValues;
	
	@GetMapping("/all")
	public String index(Model model) throws RestClientException {
		final String url = "http://" + getPropertyValues.get("server.address") + "/get/books";

		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

	    if (HttpStatus.OK == response.getStatusCode()) {
	        model.addAttribute("books", BookParser.getBooks(response.getBody()));
	        
	        BookParser.getBooks(response.getBody()).forEach(e -> System.out.println(e.getTitle()));
	    } else {
	    	throw new RestClientException("HttpStatus is not OK in index method");
	    }
		
		return "book";
	}
	
	@GetMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("book", new Book());
		
		return "add/addBook";
	}
	
	@PostMapping("/addBook")
	public String addBook(@Valid Book book, BindingResult bindingResult) throws RestClientException {
		String url = "http://" + getPropertyValues.get("server.address") + "/post/addBook";
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, book, Book.class);
		
		return "redirect:/book/all";
	}
	
	@PostMapping(value = "/removeBook")
	public String remove(@RequestParam(value="deleteButton") Long id) {
		String url = "http://" + getPropertyValues.get("server.address") + "/post/deleteBook/";

		new RestTemplate().put(url + id, id);
		
		return "redirect:/book/all";
	}
	
	@GetMapping(value = "/edit")
	public String edit(@RequestParam(value="editButton") Long id, Model model) throws RestClientException {
		model.addAttribute("book", getBookByID(id));
		
		return "edit/editBook";
	}
	
	private Book getBookByID(Long id) throws RestClientException {
		String url = "http://" + getPropertyValues.get("server.address") + "/get/book/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
	    if (HttpStatus.OK == response.getStatusCode()) {
	    	Book book = restTemplate.getForObject(url, Book.class);
	    	
			return book;
	    } else {
	        throw new RestClientException("HttpStatus is not OK in getAuthorByID method");
	    }
	}
}
