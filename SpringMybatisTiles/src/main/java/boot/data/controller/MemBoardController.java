package boot.data.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import boot.data.dto.MemBoardDto;
import boot.data.service.MemBoardService;
import boot.data.service.MemberService;

@Controller
@RequestMapping("/memboard")
public class MemBoardController {

	@Autowired
	MemBoardService service;
	
	@Autowired
	MemberService mservice;
	
	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage) {
		ModelAndView mav=new ModelAndView();
		
		//페이징처리에 필요한 변수선언
	      int totalCount=service.getTotalCount(); //전체갯수
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
	      List<MemBoardDto> list=service.getList(startNum, perPage);
	      
	      //list의 각 글에 댓글갯수 표시
			/*
			 * for(MemBoardDto d:list) { d.setAcount(adao.getAnswerList(d.getNum()).size());
			 * }
			 */
	      
	      if (endPage > totalPage)
	          endPage = totalPage;
	      
	      
	      //sex
	      //각페이지에서 보여질 시작번호   
	      int no=totalCount-(currentPage-1)*perPage;
		
		mav.addObject("totalCount", totalCount);
		mav.addObject("list", list); //댓글포함후 전달
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("totalPage", totalPage);
		mav.addObject("no", no);
		mav.addObject("currentPage", currentPage);

		mav.addObject("totalCount", totalCount);
		mav.setViewName("/memboard/memlist");
		
		return mav;
	}
	
	@GetMapping("/form")
	public String form() {
		return "/memboard/addform";
	}
	
	@PostMapping("/insert")
	public String insert(@ModelAttribute MemBoardDto dto,HttpSession session) {
		
		String path=session.getServletContext().getRealPath("/savefile");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		
		//업로드 안한경우 'no'
		if(dto.getUpload().getOriginalFilename().equals("")) {
			dto.setUploadfile("no");
		}
		else {		//업로드한 경우
			String uploadFile=sdf.format(new Date())+dto.getUpload().getOriginalFilename();
			dto.setUploadfile(uploadFile);
			try {
				dto.getUpload().transferTo(new File(path+"/"+uploadFile));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//세션에서 아이디얻어서 dto에 저장
		String myid=(String)session.getAttribute("myid");
		dto.setMyid(myid);
		
		String name = mservice.getName(myid); 	//->이방법도 가능
		//String name=(String)session.getAttribute("loginname");
		dto.setName(name);
		
		service.insertBoard(dto);
		
		return "redirect:content?num="+service.getMaxNum();
	}
	
	@GetMapping("/content")
	public ModelAndView content(@RequestParam String num,
			@RequestParam(defaultValue = "1") int currentPage) {
		ModelAndView mav=new ModelAndView();
		
		//조회수 증가
		service.updateReadCount(num);
		
		MemBoardDto dto=service.getData(num);
		mav.addObject("dto", dto);
		
		//업로드 파일의 확장자 얻기
		int dotLocation=dto.getUploadfile().lastIndexOf('.');	//마지막 점의 위치
		String ext=dto.getUploadfile().substring(dotLocation+1);	//점 다음글자부터 끝까지 추출
				
		System.out.println(dotLocation+","+ext);
				
		if(ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("gif") ||
			ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg")) {
					mav.addObject("bupload", true);
				}
		else {
			mav.addObject("bupload", false);
		}
		
		mav.addObject("currentPage", currentPage);
		mav.setViewName("/memboard/content");
		
		return mav;
	}
	
	@GetMapping("/updateform")
	public String update() {
		return "/memboard/updateform";
	}
	
	@GetMapping("/delete")
	public String delete() {
		return "/memboard/delete";
	}
}
