package pl.great.waw.company.services;


import java.math.BigDecimal;

public interface EmployeeService {
    EmployeeDto get(String pesel);

    EmployeeDto create(String firstName, String lastName, String pesel, BigDecimal salary);

    EmployeeDto update(EmployeeDto employeeDto);

    boolean delete(String pesel);
}