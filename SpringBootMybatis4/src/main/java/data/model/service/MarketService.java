package data.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import data.model.dto.MarketDto;
import data.model.mapper.MarketMapperInter;

//예전의 dao같은거 =service임 매퍼를 좀 보완해주는 역할임 전달할때 서비스 잇으면 서비스전달 없으면 매퍼 전달 
@Service 
public class MarketService implements MarketServiceInter {

	@Autowired
	MarketMapperInter mapperInter;
	
	
	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return mapperInter.getTotalCount();
	}

	@Override
	public List<MarketDto> getAllSangpum() {
		// TODO Auto-generated method stub
		return mapperInter.getAllSangpum();
	}

	@Override
	public void insertMarket(MarketDto dto) {
		// TODO Auto-generated method stub
		mapperInter.insertMarket(dto);
	}

	@Override
	public MarketDto getData(String num) {
		// TODO Auto-generated method stub
		return mapperInter.getData(num);
	}

	@Override
	public void updateMarket(MarketDto dto) {
		// TODO Auto-generated method stub
		 mapperInter.updateMarket(dto);
	}

	@Override
	public void deleteMarket(String num) {
		// TODO Auto-generated method stub
		 mapperInter.deleteMarket(num);
		
	}

}
