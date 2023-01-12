package pl.great.waw.company.services;

import org.springframework.stereotype.Service;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.model.Employee;
import pl.great.waw.company.repository.EmployeeRepository;

import java.math.BigDecimal;


@Service
public class EmployeeServiceImpl {

    EmployeeRepository employeeRepo;


    public EmployeeServiceImpl(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    public EmployeeDto update(EmployeeDto employeeDto) {
        return null;
    }


    public EmployeeDto read(String pesel) throws PeselAlreadyExistException {
        return empToDto(employeeRepo.read(pesel));
    }



    public EmployeeDto create(EmployeeDto employeeDto) throws PeselAlreadyExistException {
        Employee employee = employeeRepo.create(DtoToEmp(employeeDto));
        return empToDto(employee);
    }


    public boolean delete(String pesel) throws PeselAlreadyExistException {
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

    private Employee DtoToEmp(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getPesel(), employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getSalary());
        return employee;
    }
}
