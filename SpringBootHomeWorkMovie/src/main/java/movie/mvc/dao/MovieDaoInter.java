package movie.mvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import movie.mvc.dto.MovieDto;

public interface MovieDaoInter extends JpaRepository<MovieDto, Long>{

}
