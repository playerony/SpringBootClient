package pl.kamsoft.nfz.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.playerony.libraryV2.model.Author;
import com.playerony.libraryV2.model.Book;

import pl.kamsoft.nfz.utility.GetPropertyValues;

@Controller
@RequestMapping("/xml")
public class XmlController {
	@Autowired
	private GetPropertyValues getPropertyValues;

	@RequestMapping("/")
	public String index() {
		return "xml";
	}
	
	@PostMapping("/export")
	public String exportXml(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
	    Path path = Paths.get(file.getOriginalFilename());
	    
	    String url = "http://" + getPropertyValues.get("server.address") + "/post/export";
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<MultipartFile> requestEntity = new HttpEntity<>(file, headers);
		
		restTemplate.postForObject(url, requestEntity, String.class);
		
		return "redirect:/xml/";
	}
	
	@PostMapping("/import")
	public String importXml(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		Path path = Paths.get(file.getOriginalFilename());
		
		String url = "http://" + getPropertyValues.get("server.address") + "/post/import";
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<MultipartFile> requestEntity = new HttpEntity<>(file, headers);
		
		restTemplate.postForObject(url, requestEntity, String.class);
		
		return "redirect:/xml/";
	}
}
