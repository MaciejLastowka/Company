package pl.great.waw.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CompanyApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(CompanyApplication.class, args);
	}

}
