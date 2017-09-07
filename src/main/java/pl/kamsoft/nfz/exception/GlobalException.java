package pl.kamsoft.nfz.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(RestClientException.class)
	public String handleDatabaseException(RestClientException e, Model model) {
		model.addAttribute("message", e.getMessage());
		e.printStackTrace();
		
		return "error/errorPage";
	}
}
