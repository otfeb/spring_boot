package boot.data.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import boot.data.dto.MemberDto;
import boot.data.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	MemberService service;

	@GetMapping("/login/main")
	public String loginform(HttpSession session,Model model) {
		
		//폼의 아이디
		String myid=(String)session.getAttribute("myid");
		
		//로그인상태인지 아닌지
		String loginok=(String)session.getAttribute("loginok");
		
		//한번도 실행안하면 null
		if(loginok==null) {
			return "/login/loginform";
		}
		else {
			
			//로그인주이면 request에 name저장
			String name=service.getName(myid);
			
			model.addAttribute("name", name);
			
			return "/sub/login/logoutform";
		}
	}
	
	@PostMapping("/login/loginprocess")
	public String loginproc(@RequestParam String id,
			@RequestParam String pass,
			@RequestParam(required = false) String cbsave,
			HttpSession session) {
		HashMap<String, String> map=new HashMap<>();
		
		int check=service.loginPassCheck(id, pass);
		
		if(check==1) {
			session.setMaxInactiveInterval(60*60*8);		//8시간 유지
			
			session.setAttribute("myid", id);
			session.setAttribute("loginok", "yes");
			session.setAttribute("saveok", cbsave);
			
			MemberDto mdto=service.getData(id);
			
			session.setAttribute("loginphoto", mdto.getPhoto());
			session.setAttribute("loginname", mdto.getName());
			
			return "redirect:main";
		}
		else {
			return "/member/passfail";
		}
	}
	
	@GetMapping("/login/logoutprocess")
	public String logout(HttpSession session) {
		session.removeAttribute("loginok");
		
		return "redirect:main";
	}
}
