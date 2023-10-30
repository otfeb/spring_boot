package boot.data.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boot.data.dto.MemberDto;
import boot.data.mapper.MemberMapperInter;

@Service
public class MemberService implements MemberServiceInter {

	@Autowired
	MemberMapperInter mapperInter;
	
	@Override
	public void insertMember(MemberDto dto) {
		mapperInter.insertMember(dto);
		
	}

	@Override
	public List<MemberDto> getAllMembers() {
		
		return mapperInter.getAllMembers();
	}

	@Override
	public int getSerchId(String id) {
		
		return mapperInter.getSerchId(id);
	}

	@Override
	public MemberDto getData(String id) {
		// TODO Auto-generated method stub
		return mapperInter.getData(id);
	}

	@Override
	public String getName(String id) {
		// TODO Auto-generated method stub
		return mapperInter.getName(id);
	}

	@Override
	public int loginPassCheck(String id, String pass) {
		Map<String, String> map=new HashMap<>();
		
		map.put("id", id);
		map.put("pass", pass);
		
		return mapperInter.loginPassCheck(map);
	}

	@Override
	public void deleteMember(String num) {
		// TODO Auto-generated method stub
		mapperInter.deleteMember(num);
	}

	@Override
	public void updatePhoto(String num, String photo) {
		Map<String, String> map=new HashMap<>();
		
		map.put("num", num);
		map.put("photo", photo);
		
		mapperInter.updatePhoto(map);
	}

	@Override
	public void updateMember(MemberDto dto) {
		mapperInter.updateMember(dto);
	}

}
