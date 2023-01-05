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

    @Test
    void update() throws PeselAlreadyExistException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = new Employee("29123123", "bartek", "porebski", BigDecimal.TEN);
        Employee employee1 = new Employee("55555555", "sdddd", "sadasdsads", BigDecimal.TEN);
        employeeRepository.create(employee);
        Employee update = employeeRepository.update("29123123", employee1);
        assertEquals(employee1, update);
    }

    @Test
    void delete() throws PeselAlreadyExistException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = new Employee("29123123", "bartek", "porebski", BigDecimal.TEN);
        Employee employee1 = new Employee("55555555", "sdddd", "sadasdsads", BigDecimal.TEN);
        employeeRepository.create(employee);
        employeeRepository.create(employee1);
        employeeRepository.delete("29123123");
        assertEquals(1, employeeRepository.size());
    }
}