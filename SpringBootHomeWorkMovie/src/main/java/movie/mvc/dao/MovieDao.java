package movie.mvc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import movie.mvc.dto.MovieDto;

@Repository
public class MovieDao{
	
	@Autowired
	MovieDaoInter movieInter;
	
	//insert
	public void insertMovie(MovieDto dto) {
		movieInter.save(dto);
	}
	
	//select
	public List<MovieDto> getAllDatas(){
		return movieInter.findAll(Sort.by(Sort.Direction.DESC,"opendate"));
	}
	
	//getdata
	public MovieDto getdata(Long num) {
		return movieInter.getReferenceById(num);
	}
}
