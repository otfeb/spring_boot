package boot.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
	public List<String> result(String search) {
		List<String> list=inter.sangSearch(search);
		
		return list;
	}
}
