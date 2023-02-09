package pl.great.waw.company.IntegrationTestAPI;

import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.web.client.RestTemplate;
import pl.great.waw.company.service.EmployeeDto;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.IntStream;

public class IntegrationTest {

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    @Tag("integration")
    public void addEmployeeAPI() {
        IntStream.range(0, 100).forEach(i -> {
            Faker faker = new Faker(new Locale("pl"));
            String pesel = UUID.randomUUID().toString();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            BigDecimal salary = new BigDecimal(faker.number().randomNumber());
            EmployeeDto employeeDto = new EmployeeDto(pesel, firstName, lastName, salary,null );
            restTemplate.postForEntity("http://localhost:8081/employee", employeeDto, EmployeeDto.class);
        });
    }
}
