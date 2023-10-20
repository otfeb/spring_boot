package mycar.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/car")
public class MyCarController {

	@Autowired
	MyCarDao dao;
	
	@GetMapping("/carlist")		//시작..
	public ModelAndView list() {
		
		ModelAndView mav=new ModelAndView();
		List<MyCarDto> list=dao.getAllDates();
		
		mav.addObject("list", list);
		mav.addObject("totalCount", list.size());
		
		mav.setViewName("carlist");
		
		return mav;
	}
	
	@GetMapping("/carform")
	public String form() {
		return "addform";
	}
	
	//insert
	@PostMapping("/insert")
	public String insert(MyCarDto dto) {
		
		dao.insertMyCar(dto);
		
		return "redirect:carlist";
	}
	
	//getdata
	@GetMapping("/updateform")
	public ModelAndView getdata(Long num) {
		ModelAndView mav=new ModelAndView();
		
		MyCarDto dto=dao.getdata(num);
		
		mav.addObject("num", num);
		mav.addObject("carname", dto.getCarname());
		mav.addObject("carprice", dto.getCarprice());
		mav.addObject("carcolor", dto.getCarcolor());
		mav.addObject("carguip", dto.getCarguip());
		
		mav.setViewName("uform");
		
		return mav;
	}
	
	//update
	@PostMapping("/update")
	public String updateCar(MyCarDto dto) {
		
		dao.updateCar(dto);
		
		return "redirect:carlist";
	}
	
	//delete
	@GetMapping("/delete")
	public String deleteCar(Long num) {
		dao.deleteCar(num);
		
		return "redirect:carlist";
	}
}
