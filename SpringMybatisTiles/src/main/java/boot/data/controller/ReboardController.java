package boot.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReboardController {

	@GetMapping("/board/list")
	public String boardlist() {
		return "/reboard/boardlist";
	}
}
