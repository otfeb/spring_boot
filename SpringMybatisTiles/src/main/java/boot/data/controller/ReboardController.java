package boot.data.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import boot.data.dto.ReboardDto;
import boot.data.service.ReboardService;

@Controller
@RequestMapping("/reboard")
public class ReboardController {
	
	@Autowired
	ReboardService service;

	@GetMapping("/list")
	public ModelAndView boardlist(@RequestParam(defaultValue = "1") int currentPage,
			@RequestParam(value = "searchcolumn",required = false) String sc,
			@RequestParam(value = "searchword",required = false) String sw) {
		
		ModelAndView mav=new ModelAndView();
		
		//페이징처리에 필요한 변수선언
	      int totalCount=service.getTotalCount(sc,sw); //전체갯수
	      int totalPage; //총 페이지
	      int startPage; //각블럭에서 보여질 시작페이지
	      int endPage; //각블럭에서 보여질 끝페이지
	      int startNum; //db에서 가져올 글의 시작번호(mysql은 첫글이 0,오라클은 1)
	      int perPage=5; //한페이지당 보여질 글의 갯수
	      int perBlock=5; //한블럭당 보여질 페이지 개수
	      
	      //나머지가 1이라도 있으면 무조건 1페이지 추가(1+1=2페이지가 필요)
	        totalPage=totalCount/perPage+(totalCount%perPage==0?0:1);
	        
	        //각블럭당 보여야할 시작페이지
	        //perBlock=5일경우는 현재페이지 1~5 시작:1 끝:5
	        //현재페이지 13  시작:11  끝:15
	        startPage=(currentPage-1)/perBlock*perBlock+1;
	          
	        endPage=startPage+perBlock-1;

	      //1페이지: 0,2페이지:5 3페이지:10....
	      startNum=(currentPage-1)*perPage;
	      
	      //각페이지에서 필요한 게시글 가져오기
	      List<ReboardDto> list=service.getPagingList(sc, sw, startNum, perPage);
	      
	      
	      if (endPage > totalPage)
	          endPage = totalPage;
	      
	      
	      //각페이지에서 보여질 시작번호   
	      int no=totalCount-(currentPage-1)*perPage;
		
		mav.addObject("totalCount", totalCount);
		mav.addObject("list", list); //댓글포함후 전달
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("totalPage", totalPage);
		mav.addObject("no", no);
		mav.addObject("currentPage", currentPage);
		
		System.out.println("totalCount="+totalCount);
		
		mav.setViewName("/reboard/boardlist");
		
		return mav;
	}
	
	@GetMapping("/form")
	public String reform(@RequestParam(defaultValue = "0") int num,
			@RequestParam(defaultValue = "0") int regroup,
			@RequestParam(defaultValue = "0") int restep,
			@RequestParam(defaultValue = "0") int relevel,
			@RequestParam(defaultValue = "1") int currentPage,Model model) {
		
		//답글일경우에만 넘어오는 값들이다
		
		//새글일경우는 모두 null이므로 defaultValue만 값으로 전달
		model.addAttribute("num", num);
		model.addAttribute("regroup", regroup);
		model.addAttribute("restep", restep);
		model.addAttribute("relevel", relevel);
		model.addAttribute("currentPage", currentPage);
		
		//새글일경우는 "",답글일경우에는 원글 제목 가져오기
		String subject="";
		if(num>0) {
			subject=service.getData(num).getSubject();
		}
		
		return "/reboard/addform";
	}
	
	@PostMapping("/insert")
	public String insert(List<MultipartFile> upload,
			HttpSession session,
			@ModelAttribute ReboardDto dto) {
		
		String path=session.getServletContext().getRealPath("/rephoto");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String uploadName="";
		
		if(upload.get(0).getOriginalFilename().equals("")) {	//사진 선택 안한경우
			uploadName=null;
		}
		else {
			for(MultipartFile f:upload) {
				String fName=sdf.format(new Date())+f.getOriginalFilename();
				uploadName+=fName+",";
				try {
					f.transferTo(new File(path+"/"+fName));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//photo 마지막 콤마 제거
			uploadName=uploadName.substring(0,uploadName.length()-1);
		}
		
		dto.setPhoto(uploadName);
		
		//myid,loginname 세션에 저장
		String myid=(String)session.getAttribute("myid");
		dto.setId(myid);
		
		String loginname=(String)session.getAttribute("loginname");
		dto.setName(loginname);
		
		service.insertReboard(dto);
		
		return "redirect:content";
	}
	
	@GetMapping("/content")
	public String content() {
		return "/reboard/content";
	}
	
	
	
}
