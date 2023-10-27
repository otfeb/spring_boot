package boot.data.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import boot.data.dto.MemberDto;
import boot.data.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	@GetMapping("/member/myinfo")
	public ModelAndView memberform(@RequestParam String id) {
		
		ModelAndView mav=new ModelAndView();
		
		MemberDto dto=service.getData(id);
		
		mav.addObject("dto", dto);
		
		mav.setViewName("/member/myinfo");
		
		return mav;
	}
	
	@GetMapping("/member/list")
	public ModelAndView memberlist() {
		
		ModelAndView mav=new ModelAndView();
		
		List<MemberDto> list=service.getAllMembers();
		
		mav.addObject("list", list);
		mav.addObject("totalCount", list.size());
		
		mav.setViewName("/member/memberlist");
		
		return mav;
	}
	
	@GetMapping("/member/add")
	public String add() {
		return "/member/addform";
	}
	
	//아이디체크
	@GetMapping("/member/idcheck")
	@ResponseBody
	public Map<String, Integer> idcheck(@RequestParam String id) {
		
		Map<String, Integer> map=new HashMap<>();
		
		int check=service.getSerchId(id);
		
		map.put("count", check);	//0 or 1
		
		return map;
	}
	
	//insert(list로 가는데 admin이 아니면 gaipsuccess로 이동)
	@PostMapping("/member/insert")
	public String insert(@ModelAttribute MemberDto dto,
			MultipartFile myphoto,
			HttpSession session, Model model) {
		
		
		String path=session.getServletContext().getRealPath("/upload");
		
		dto.setPhoto(myphoto.getOriginalFilename());
		
		try {
			myphoto.transferTo(new File(path+"/"+myphoto.getOriginalFilename()));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("dto", dto);
		
		if(!dto.getId().equals("admin")) {
			service.insertMember(dto);
			return "/member/gaipsuccess";
		}
		else {
			service.insertMember(dto);
			return "redirect:list";
		}
	}
}
