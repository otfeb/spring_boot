package hello.boot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/sist/hello")
	public Map<String, String> hello(){
		Map<String, String> data=new HashMap<>();
		
		data.put("message", "오늘은 스프링부트 배우는날");
		
		return data;
	}
}
