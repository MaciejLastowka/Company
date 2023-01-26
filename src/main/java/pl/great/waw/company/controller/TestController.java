package pl.great.waw.company.controller;

import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.service.EmployeeDto;

import java.math.BigDecimal;
import java.util.Locale;


public class TestController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping()
    int add100Employees() throws PeselAlreadyExistException {
        Faker faker = new Faker(new Locale("pl"));
        for (int i = 0; i < 100; i++) {
            String pesel = String.valueOf(100 + i);
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            BigDecimal salary = new BigDecimal(faker.number().randomNumber());
            EmployeeDto employeeDto = new EmployeeDto(pesel, firstName, lastName, salary);
            restTemplate.postForEntity("http://localhost:8081/employee", employeeDto, EmployeeDto.class);
        }
        return 100;
    }
}
