package mycar.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MyCarDao{

	@Autowired
	MyCarDaoInter carInter;
	
	//insert
	public void insertMyCar(MyCarDto dto) {
		carInter.save(dto);		//둘다 save(id타입 유무에 따라 자동으로 insert 또는 update 구별)
	}
	
	//전체출력
	public List<MyCarDto> getAllDates(){
		return carInter.findAll();
	}
	
	//getdata
	public MyCarDto getdata(Long num) {
		return carInter.getReferenceById(num);
	}
	
	//update
	public void updateCar(MyCarDto dto) {
		carInter.save(dto);
	}
	
	//delete
	public void deleteCar(Long num) {
		carInter.deleteById(num);
	}
	 
}
