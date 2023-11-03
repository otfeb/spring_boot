package boot.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SmartController {

	@GetMapping("/smart/list")
	public ModelAndView list() {
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("/smartshop/list");
		
		return mav;
	}
	
	@GetMapping("/smart/form")
	public String form() {
		return "/smartshop/smartform";
	}
}
