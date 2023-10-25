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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import boot.data.dto.IpgoDto;
import boot.data.mapper.ipgoMapperInter;

@Controller

public class IpgoController {
	
	@Autowired
	ipgoMapperInter mapper;
	
	@GetMapping("/")
	public String start()
	{
		return "/layout/main";
	}
	
	
	
  @GetMapping("/ipgo/list")
	public ModelAndView list()
	{
		ModelAndView model = new ModelAndView();
		
		int totalcount = mapper.getTotalCount();
		List<IpgoDto>list = mapper.getAllIpgos();
		
		model.addObject("totalCount", totalcount);
		model.addObject("list", list);
		//model.setViewName("ipgolist"); jsp
		model.setViewName("/ipgo/ipgolist");//tiles 경로로 찾아감
		return model;
	}
	
	
	@GetMapping("/ipgo/ipgoform")
	public String form()
	{
		return "/ipgo/ipgoform";
	}
	
	
	//사진여러개 하고싶으면 arraylist 멀티파일 임
	@PostMapping("/ipgo/insert")
	public String insert(@ModelAttribute IpgoDto dto,
			@RequestParam ArrayList<MultipartFile> photo,
			HttpSession session)
	{
		String path = session.getServletContext().getRealPath("/upload");
		System.out.println(path);
		
		String uploadName= "";
		
		if(photo.get(0).getOriginalFilename().equals(""))
			uploadName="no";
		else {
			for(MultipartFile f:photo)
			{
				SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
				String fName = sdf.format(new Date())+"_"+f.getOriginalFilename();
				uploadName+=fName+",";
				
				//업로드
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
			//photo 마지막 컴마 제거
			uploadName=uploadName.substring(0, uploadName.length()-1);
		}
		dto.setPhotoname(uploadName);
		mapper.insertIpgo(dto);
		
		return "redirect:list";
	}
	@GetMapping("/ipgo/content")
	public ModelAndView content(@RequestParam String num)
	{
		ModelAndView model = new ModelAndView();
		
		IpgoDto dto=mapper.getData(num);
		
		model.addObject("dto", dto);
		model.setViewName("detail");
		
		return model;
	}
	
}
