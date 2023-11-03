package boot.data.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import boot.data.dto.MbanswerDto;
import boot.data.mapper.MbanswerMapperInter;
import boot.data.service.MemberService;

@RestController
@RequestMapping("/mbanswer")
public class MbanswerController {

	@Autowired
	MbanswerMapperInter mapper;
	
	@Autowired
	MemberService mservice;
	
	@PostMapping("/ainsert")
	public void insert(@ModelAttribute MbanswerDto dto,HttpSession session) {
		String myid=(String)session.getAttribute("myid");
		
		String name=mservice.getName(myid);
		
		dto.setMyid(myid);
		dto.setName(name);
		
		mapper.insertMbanswer(dto);
	}
	
	@GetMapping("/alist")
	public List<MbanswerDto> list(String num){
		return mapper.getAllAnswers(num);
	}
	
	
	 @GetMapping("/adelete") 
	 public void delete(String idx) {
		 mapper.deleteAnswer(idx); 
	}
	 
	 //수정창 content띄우기
	 @GetMapping("/adata")
	 public MbanswerDto getData(String idx) {
		 return mapper.getAnswer(idx);
	 }
	 
	 //수정
	 @PostMapping("/aupdate")
	 public void aupdate(@ModelAttribute MbanswerDto dto) {
		 mapper.updateMbanswer(dto);
	 }
	 
}
