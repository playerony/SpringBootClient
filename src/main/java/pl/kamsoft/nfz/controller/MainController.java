package pl.kamsoft.nfz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String index(){
		return "redirect:/login";
	}
	
	@RequestMapping("/access-denied")
	public String accessDenied(Model model){
		model.addAttribute("message", "access-denied");
		
		return "error/errorPage";
	}
	
}
