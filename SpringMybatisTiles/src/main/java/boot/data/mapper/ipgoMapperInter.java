package boot.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import boot.data.dto.IpgoDto;

@Mapper
public interface ipgoMapperInter {

	public int getTotalCount();		//sql의 id
	public void insertIpgo(IpgoDto dto);
	public List<IpgoDto> getAllIpgos();
	public IpgoDto getData(String num);
}
