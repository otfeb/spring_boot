package boot.last.mini;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("boot.data.*")
@ComponentScan("boot.last.mini")
@MapperScan("boot.data.mapper")
public class SpringMybatisTilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMybatisTilesApplication.class, args);
	}

}
