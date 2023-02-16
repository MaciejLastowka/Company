package pl.great.waw.company.repository;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.great.waw.company.exceptions.IdNotFoundException;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.model.Employee;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;
    private Employee employee;
    private static final int TEST_DATA_COUNT = 10;

    @BeforeEach
    void setUp() {
        this.employeeRepository = new EmployeeRepository();
        this.employee = new Employee("29123123", "bartek", "porebski", BigDecimal.TEN);
    }

    @Test
    void create() throws PeselAlreadyExistException {

        Employee employee1 = employeeRepository.create(employee);
        assertEquals(employee1, employee);

    }

    @Test
    void read() throws PeselAlreadyExistException, IdNotFoundException {

        employeeRepository.create(employee);
        Employee read = employeeRepository.read("29123123");
        assertEquals(employee, read);
    }

    @Test
    void update() throws PeselAlreadyExistException, IdNotFoundException {

        Employee employee1 = new Employee("29123123", "sdddd", "sadasdsads", BigDecimal.TEN);
        employeeRepository.create(employee);
        Employee update = employeeRepository.update( employee1);
        assertEquals(employee1, update);
    }

    @Test
    void delete() throws PeselAlreadyExistException, IdNotFoundException {

        Employee employee1 = new Employee("29123123", "sdddd", "sadasdsads", BigDecimal.TEN);
        employeeRepository.create(employee);
        employeeRepository.create(employee1);
        employeeRepository.delete("29123123");
        assertEquals(1, employeeRepository.size());
    }

    @Test
    void random() {

        Faker faker = new Faker(new Locale("pl"));
        EmployeeRepository employeeRepository = new EmployeeRepository();

        IntStream.range(0, TEST_DATA_COUNT).forEach((i) -> {

            String pesel = faker.idNumber().invalid();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            BigDecimal salary = new BigDecimal(faker.number().randomNumber());
            Employee employee = new Employee(pesel, firstName, lastName, salary);
            try {
                employeeRepository.create(employee);
            } catch (PeselAlreadyExistException e) {
                e.printStackTrace();
            }
        });

        assertEquals(TEST_DATA_COUNT, employeeRepository.size());
    }
}