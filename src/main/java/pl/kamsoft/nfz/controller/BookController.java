package pl.kamsoft.nfz.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.playerony.libraryV2.model.Book;

import pl.kamsoft.nfz.parser.BookParser;
import pl.kamsoft.nfz.utility.GetPropertyValues;


@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	private GetPropertyValues getPropertyValues;
	
	@GetMapping("/all")
	public String index(Model model) throws IOException {
		final String uri = "http://" + getPropertyValues.get("server.address") + "/get/books";

		System.out.println(uri);
		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

	    if (HttpStatus.OK == response.getStatusCode()) {
	        System.out.println(response.toString());
	        model.addAttribute("books", BookParser.getBooks(response.getBody()));
	        
	        BookParser.getBooks(response.getBody()).forEach(e -> System.out.println(e.getTitle()));
	    } else {
	        System.out.println("error");
	    }
		
		return "book";
	}
	
	@GetMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("book", new Book());
		
		return "add/addBook";
	}
	
	@PostMapping("/addBook")
	public String addBook(@Valid Book book, BindingResult bindingResult) throws IOException {
		String url = "http://" + getPropertyValues.get("server.address") + "/post/addBook";
		
		System.out.println(book.toString());
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, book, Book.class);
		
		return "redirect:/book/all";
	}
	
	@PostMapping(value = "/removeBook")
	public String remove(@RequestParam(value="deleteButton") Long id) throws IOException {
		String url = "http://" + getPropertyValues.get("server.address") + "/post/deleteBook/";

		new RestTemplate().put(url + id, id);
		
		return "redirect:/book/all";
	}
	
	@GetMapping(value = "/edit")
	public String edit(@RequestParam(value="editButton") Long id, Model model) throws IOException {
		model.addAttribute("book", getBookByID(id));
		return "edit/editBookr";
	}
	
	private Book getBookByID(Long id) throws IOException {
		String url = "http://" + getPropertyValues.get("server.address") + "/get/book/{id}";

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id.toString());

		RestTemplate restTemplate = new RestTemplate();
		Book book = restTemplate.getForObject(url, Book.class, params);
		System.out.println(book.toString());
		
		return book;
	}
}
