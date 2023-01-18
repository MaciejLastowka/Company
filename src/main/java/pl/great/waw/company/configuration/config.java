package pl.great.waw.company.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.great.waw.company.repository.EmployeeRepository;


@Configuration
public class config {

    @Bean
    public  EmployeeRepository employeeRepository(){
        return new EmployeeRepository();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}