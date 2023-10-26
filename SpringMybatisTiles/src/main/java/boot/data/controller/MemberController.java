package boot.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

	@GetMapping("/member/form")
	public String memberform() {
		return "/member/addform";
	}
	
	@GetMapping("/member/list")
	public String memberlist() {
		return "/member/memberlist";
	}
}
