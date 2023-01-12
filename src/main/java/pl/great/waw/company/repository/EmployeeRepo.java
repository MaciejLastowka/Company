package pl.great.waw.company.repository;


import org.springframework.stereotype.Service;
import pl.great.waw.company.model.Employee;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface EmployeeRepo {

    Employee create(String firstName, String lastName, String pesel, BigDecimal salary);

    Employee get(String pesel);

    List<Employee> getAll();

    boolean delete(String pesel);

    Employee update(Employee employee);

    int size();

    boolean clear();
}

