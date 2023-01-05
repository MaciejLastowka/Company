package pl.great.waw.company.repository;

import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.model.Employee;

import java.util.ArrayList;
import java.util.List;


public class EmployeeRepository {
    private final List<Employee> list = new ArrayList<>();

    public Employee create(Employee employee) throws PeselAlreadyExistException {
        if (list.contains(employee)) {
            throw new PeselAlreadyExistException(("This pesel already exist: " + employee.getPesel()));
        }
        list.add(employee);
        return employee;
    }
}
