package pl.kamsoft.nfz.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.playerony.libraryV2.model.Author;
import com.playerony.libraryV2.model.Book;

@Controller
@RequestMapping("/xml")
public class XmlController {

	@RequestMapping("/")
	public String index() {
		return "xml";
	}
	
	@PostMapping("/export")
	public String exportXml(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
	    Path path = Paths.get(file.getOriginalFilename());
	    
	    String url = "http://localhost:8080/post/export";
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, path.toString(), String.class);
		
		return "redirect:/xml/";
	}
	
	@PostMapping("/import")
	public String importXml(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		Path path = Paths.get(file.getOriginalFilename());
		
		String url = "http://localhost:8080/post/import";
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, path.toString(), String.class);
		
		return "redirect:/xml/";
	}
}
