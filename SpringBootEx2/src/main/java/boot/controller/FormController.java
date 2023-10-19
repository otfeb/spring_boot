package boot.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import boot.dto.PersonDto;

@Controller
public class FormController {

	@GetMapping("/sist/form1")
	public String form1() {
		return "form/form1";
	}
	
	@GetMapping("/sist/form2")
	public String form2() {
		return "form/form2";
	}
	
	@GetMapping("/sist/form3")
	public String form3() {
		return "form/form3";
	}
	
	@PostMapping("/sist/read1")
	public ModelAndView read1(String irum,double java,double spring) {
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("irum", irum);
		mav.addObject("java", java);
		mav.addObject("spring", spring);
		mav.addObject("total", java+spring);
		mav.addObject("avg", (java+spring)/2);
		
		mav.setViewName("result/result1");
		
		return mav;
	}
	
	@PostMapping("/sist/write2")
	public ModelAndView write2(PersonDto dto) {
		
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("name", dto.getName());
		mav.addObject("addr", dto.getAddr());
		mav.addObject("hp", dto.getHp());
		
		mav.setViewName("result/result2");
		
		return mav;
	}
	
	//map으로 읽을경우 폼의 name이 key값으로 입력값은 value
	@PostMapping("sist/map3")
	public ModelAndView map3(@RequestParam HashMap<String, String> map) {
		ModelAndView mav=new ModelAndView();

		String name=map.get("name");
		String blood=map.get("blood");
		String age=map.get("age");
		
		mav.addObject("name", name);
		mav.addObject("blood", blood);
		mav.addObject("age", age);
		
		mav.addObject("map", map);
		
		mav.setViewName("result/result3");
		
		return mav;
	}
}
