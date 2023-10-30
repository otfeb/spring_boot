package boot.data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import boot.data.dto.MemberDto;

@Mapper
public interface MemberMapperInter {

	public void insertMember(MemberDto dto);
	public List<MemberDto> getAllMembers();
	public int getSerchId(String id);		//아이디 존재여부
	public MemberDto getData(String id);
	public String getName(String id);
	public int loginPassCheck(Map<String, String> map);
	public void deleteMember(String num);
	public void updatePhoto(Map<String, String> map);
	public void updateMember(MemberDto dto);
}
