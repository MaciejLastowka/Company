package pl.great.waw.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.model.Employee;

import pl.great.waw.company.repository.EmployeeRepo;

import java.math.BigDecimal;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public EmployeeDto get(String pesel) {
        return empToDto(employeeRepo.get(pesel));
    }


    @Override
    public EmployeeDto create(String firstName, String lastName, String pesel, BigDecimal salary) {
        Employee employee = employeeRepo.create(firstName, lastName, pesel, salary);
        return empToDto(employee);
    }


    public boolean delete(String pesel) {
        return employeeRepo.delete(pesel);

    }

    private EmployeeDto empToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setPesel(employee.getPesel());
        employeeDto.setSalary(employee.getPrice());
        return employeeDto;
    }

    public EmployeeDto create(EmployeeDto employeeDto) {
        return employeeDto;
    }
}
