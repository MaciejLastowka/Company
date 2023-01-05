package pl.great.waw.company.repository;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.NotNull;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.model.Employee;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest {

    @Test
    void create() throws PeselAlreadyExistException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = new Employee("22222222", "bartek", "porebski", BigDecimal.TEN);

        Employee employee1 = employeeRepository.create(employee);
        assertEquals(employee1, employee);

    }

    @Test
    void read() throws PeselAlreadyExistException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = new Employee("29123123", "bartek", "porebski", BigDecimal.TEN);
        employeeRepository.create(employee);
        Employee read = employeeRepository.read("29123123");
        assertEquals(employee, read);
    }
}