package data.model.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import data.model.dto.MarketDto;
import data.model.mapper.MarketMapperInter;

@Controller
@RequestMapping("/market")
public class MarketController {

	@Autowired
	MarketMapperInter mapper;
	
	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView mav=new ModelAndView();
		
		//db로 부터 총개수 얻기
		int totalCount=mapper.getTotalCount();
		
		List<MarketDto> list=mapper.getAllSangpum();
		
		//저장
		mav.addObject("totalCount", totalCount);
		mav.addObject("list", list);
		
		//포워드
		mav.setViewName("market/marketlist");
		
		return mav;
	}
	
	@GetMapping("/writeform")
	public String writeform() {
		return "market/addform";
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute(value="dto") MarketDto dto,
			MultipartFile upload,
			HttpSession session) {
		
		String path=session.getServletContext().getRealPath("/save");
		
		dto.setPhotoname(upload.getOriginalFilename());
		
		try {
			upload.transferTo(new File(path+"/"+upload.getOriginalFilename()));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mapper.insertMarket(dto);
		
		return "redirect:list";
	}
	
	
}
