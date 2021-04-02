package se.lexicon.group.Thymeleafmvcjpahotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.lexicon.group.Thymeleafmvcjpahotel.data.MyDataSource;

import java.sql.SQLException;

@SpringBootApplication
public class ThymeleafMvcJpaHotelApplication {

	public static void main(String[] args) throws SQLException {
		MyDataSource.getConnection();
		SpringApplication.run(ThymeleafMvcJpaHotelApplication.class, args);
	}

}
