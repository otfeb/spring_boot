package boot.data.service;

import java.util.List;

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

}
