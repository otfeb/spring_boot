package movie.mvc.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import movie.mvc.dao.MovieDao;
import movie.mvc.dto.MovieDto;

@Controller
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	MovieDao dao;
	
	@GetMapping("/list")		//시작페이지
	public ModelAndView list() {
		
		ModelAndView mav=new ModelAndView();
		
		List<MovieDto> list=dao.getAllDatas();
		
		mav.setViewName("movielist");
		mav.addObject("list", list);
		mav.addObject("listSize", list.size());
		
		return mav;
	}
	
	@GetMapping("/writeform")
	public String write() {
		return "addform";
	}
	
	//insert
	@PostMapping("/insert")
	public String insert(@ModelAttribute MovieDto dto,
			MultipartFile upload,
			HttpSession session) {
		
		String path=session.getServletContext().getRealPath("/moviephoto");
		
		dto.setPoster(upload.getOriginalFilename());
		
		try {
			upload.transferTo(new File(path+"/"+upload.getOriginalFilename()));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dao.insertMovie(dto);
		
		return "redirect:list";
	}
	
	//detailPage
	@GetMapping("/detail")
	public ModelAndView getdata(@RequestParam Long num) {
		ModelAndView mav=new ModelAndView();
		
		MovieDto dto=dao.getdata(num);
		
		mav.addObject("dto", dto);
		
		mav.setViewName("detail");
		
		return mav;
	}
}
