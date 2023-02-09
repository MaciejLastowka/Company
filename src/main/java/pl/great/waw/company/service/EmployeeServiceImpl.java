package pl.great.waw.company.service;

import org.springframework.stereotype.Service;
import pl.great.waw.company.exceptions.MonthAlreadyAddedException;
import pl.great.waw.company.exceptions.MonthNotFoundException;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.exceptions.PeselNotFoundException;
import pl.great.waw.company.model.Employee;
import pl.great.waw.company.model.EmployeeMonthlyData;
import pl.great.waw.company.repository.EmployeeDataRepo;
import pl.great.waw.company.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

import static pl.great.waw.company.Mapper.MapperEmployee.dtoToEmp;
import static pl.great.waw.company.Mapper.MapperEmployee.empToDto;
import static pl.great.waw.company.Mapper.MapperEmployeeData.dtoToEmpData;
import static pl.great.waw.company.Mapper.MapperEmployeeData.empDataToDto;


@Service
public class EmployeeServiceImpl {
    EmployeeRepository employeeRepo;
    EmployeeDataRepo employeeDataRepo;

    public EmployeeServiceImpl(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public EmployeeDto update(String pesel, EmployeeDto employeeDto) throws PeselNotFoundException {
        Employee employee = dtoToEmp(employeeDto);
        Employee update = employeeRepo.update(pesel, employee);
        return empToDto(update);
    }

    public List<EmployeeDto> getAll() {
        return employeeRepo.getAll()
                .stream()
                .map((employee -> {
                    return empToDto(employee);
                }))
                .collect(Collectors.toList());
    }

    public EmployeeDto read(String pesel) throws PeselNotFoundException {
        return empToDto(employeeRepo.read(pesel));
    }

    public EmployeeDto create(EmployeeDto employeeDto) throws PeselAlreadyExistException {
        Employee employee = employeeRepo.create(dtoToEmp(employeeDto));
        return empToDto(employee);
    }

    public boolean delete(String pesel) throws PeselNotFoundException {
        return employeeRepo.delete(pesel);
    }

    public boolean isPeselAlreadyExist(String pesel) {
        return employeeRepo.isPeselAlreadyExist(pesel);
    }

    public EmployeeMonthlyData createData(EmployeeDataDto employeeDataDto) throws MonthAlreadyAddedException {
        EmployeeMonthlyData employeeMonthlyData = employeeDataRepo.createData(dtoToEmpData(employeeDataDto));
        return employeeMonthlyData;
    }

    public EmployeeDataDto readData(String employeeId, int month, int year) throws MonthNotFoundException {
        return empDataToDto(employeeDataRepo.readData(employeeId, month, year));
    }

    public boolean isEmployeeIdAlreadyExist(String employeeId) {
        return employeeDataRepo.isEmployeeIdAlreadyExist(employeeId);
    }


}


