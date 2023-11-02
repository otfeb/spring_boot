package boot.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import boot.data.mapper.ipgoMapperInter;

@Controller
public class SearchController {
	
	@Autowired
	ipgoMapperInter inter;

	@GetMapping("/search/sangsearch")
	public String search() {
		return "/search/sangsearch";
	}
	
	@GetMapping("/search/result")
	@ResponseBody
	public List<String> result(String search) {
		return inter.sangSearch(search);
	}
}
