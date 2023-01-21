package pl.great.waw.company.service;

import org.springframework.stereotype.Service;
import pl.great.waw.company.controller.MapperEmployee.MapperEmployee;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.model.Employee;
import pl.great.waw.company.repository.EmployeeRepository;
import pl.great.waw.company.service.EmployeeDto;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl extends MapperEmployee {

    EmployeeRepository employeeRepo;

    public EmployeeServiceImpl(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public EmployeeDto update(String pesel, EmployeeDto employeeDto) throws PeselAlreadyExistException {
        Employee employee = dtoToEmp(employeeDto);
        Employee update = employeeRepo.update(pesel, employee);
        return empToDto(update);
    }

    public List<EmployeeDto> getAll() {
        return employeeRepo.getAll()
                .stream()
                .map((employee -> {return empToDto(employee);}))
                .collect(Collectors.toList());
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

}
