package ec.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

	
	@GetMapping("/Menu")
	public String menuPrincipal() {
		return "menuPrincipal";
	}
	
	
	
}