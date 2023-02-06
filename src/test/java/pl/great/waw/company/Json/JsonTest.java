package pl.great.waw.company.Json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.model.Employee;
import pl.great.waw.company.repository.EmployeeRepository;
import pl.great.waw.company.repository.FilePersistanceUtil;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JsonTest {

    @Autowired
    private JsonParsing jsonParsing;

    @Autowired
    private FilePersistanceUtil filePersistanceUtil;

    @Test
    void parseEmployeesTest() throws PeselAlreadyExistException, JsonProcessingException {
        //given
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = new Employee("29123123", "bartek", "porebski", BigDecimal.TEN);
        Employee employee1 = employeeRepository.create(employee);
        List<Employee> employees = employeeRepository.getAll();
        //when
        String parsedEmployees = jsonParsing.parseEmployee(employees);
        //then
        assertNotNull(parsedEmployees);
        assertFalse(parsedEmployees.isEmpty());
    }


    @Test
    void parseEmployeeFromJsonStringTest() throws JsonProcessingException {
        //given
        String jsonString = "[{\"pesel\":29123123,\"firstName\":\"bartek\",\"lastName\":\"porebski\",\"price\":10, \"created\":null, \"updated\":null}," +
                "{\"pesel\":11111111,\"firstName\":\"bartek\",\"lastName\":\"porebski\",\"price\":10, \"created\":null, \"updated\":null}]";
        List<Employee> expectedEmployees = Arrays.asList(
                new Employee("29123123", "bartek", "porebski", BigDecimal.TEN, null, null),
                new Employee("11111111", "bartek", "porebski", BigDecimal.TEN, null, null)
        );
        //when
        ObjectMapper objectMapper = new ObjectMapper();
        List<Employee> employees = objectMapper.readValue(jsonString, new TypeReference<List<Employee>>() {
        });
        //then
        assertEquals(expectedEmployees, employees);
    }

    @Test
    void writeEmployeeToFile() throws IOException {
        //given
        List<Employee> employeeList = Arrays.asList(
                new Employee("29123123", "bartek", "porebski", BigDecimal.TEN),
                new Employee("11111111", "bartek", "porebski", BigDecimal.TEN));
        //when
        filePersistanceUtil.saveDataToFile("employee_list_TEST.json", employeeList);
        File file = new File("employee_list_TEST.json");
        //then
        assertTrue(file.exists());

    }
}