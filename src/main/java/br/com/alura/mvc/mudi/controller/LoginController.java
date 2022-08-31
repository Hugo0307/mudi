package br.com.alura.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping
	public String other() {
		return login();
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
