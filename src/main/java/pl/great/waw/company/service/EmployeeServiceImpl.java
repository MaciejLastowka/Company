package pl.great.waw.company.services;

import org.springframework.stereotype.Service;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.model.Employee;
import pl.great.waw.company.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl {

    EmployeeRepository employeeRepo;

    public EmployeeServiceImpl(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public EmployeeDto update(String pesel, EmployeeDto employeeDto) throws PeselAlreadyExistException {
        Employee employee = dtoToEmp(employeeDto);
        Employee update = employeeRepo.update(pesel, employee);
        return empToDto(update);
    }

    public EmployeeDto read(String pesel) throws PeselAlreadyExistException {
        return empToDto(employeeRepo.read(pesel));
    }

    public EmployeeDto create(EmployeeDto employeeDto) throws PeselAlreadyExistException {
        Employee employee = employeeRepo.create(dtoToEmp(employeeDto));
        return empToDto(employee);
    }

    public boolean delete(String pesel) throws PeselAlreadyExistException {
        return employeeRepo.delete(pesel);
    }
    public boolean isPeselAlreadyExist(String pesel) {
        return employeeRepo.isPeselAlreadyExist(pesel);
    }

    private EmployeeDto empToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setPesel(employee.getPesel());
        employeeDto.setSalary(employee.getPrice());
        return employeeDto;
    }

    private Employee dtoToEmp(EmployeeDto employeeDto) {
        return new Employee(employeeDto.getPesel(), employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getSalary());
    }
}
