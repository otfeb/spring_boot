package boot.mvc.Movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("movie.mvc.*")
@EntityScan("movie.mvc.dto")
@EnableJpaRepositories("movie.mvc.dao")
public class SpringBootHomeWorkMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHomeWorkMovieApplication.class, args);
	}

}
