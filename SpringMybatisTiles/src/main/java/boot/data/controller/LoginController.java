package boot.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login/main")
	public String loginform() {
		return "/sub/login/loginform";
	}
	
	@GetMapping("/login/gaip")
	public String gaip() {
		return "/sub/login/registration";
	}
}
