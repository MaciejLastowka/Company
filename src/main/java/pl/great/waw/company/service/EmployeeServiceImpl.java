package pl.great.waw.company.service;

import org.springframework.stereotype.Service;
import pl.great.waw.company.exceptions.*;
import pl.great.waw.company.model.Employee;
import pl.great.waw.company.model.EmployeeMonthlyData;
import pl.great.waw.company.repository.EmployeeDataRepo;
import pl.great.waw.company.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.great.waw.company.Mapper.MapperEmployee.dtoToEmp;
import static pl.great.waw.company.Mapper.MapperEmployee.empToDto;
import static pl.great.waw.company.Mapper.MapperEmployeeData.dtoToEmpData;


@Service
public class EmployeeServiceImpl {

    private final EmployeeRepository employeeRepo;
    private final EmployeeDataRepo employeeDataRepo;

    public EmployeeServiceImpl(EmployeeRepository employeeRepo, EmployeeDataRepo employeeDataRepo) {
        this.employeeRepo = employeeRepo;
        this.employeeDataRepo = employeeDataRepo;
    }

    public EmployeeDto create(EmployeeDto employeeDto) throws PeselAlreadyExistException {
        Employee employee = employeeRepo.create(dtoToEmp(employeeDto));
        return empToDto(employee, new ArrayList<>());
    }

    public EmployeeMonthlyData createData(EmployeeDataDto employeeDataDto) throws MonthAlreadyAddedException, IdNotFoundException, MonthNotFoundException {

        this.employeeRepo.read(employeeDataDto.getEmployeeId());

        List<EmployeeMonthlyData> employeeMonthlyDataListFromDb = this.employeeDataRepo.readData(employeeDataDto.getEmployeeId());

        List<EmployeeMonthlyData> employeeMonthlyDataList = employeeMonthlyDataListFromDb.stream()
                .filter(
                        employeeMonthlyData1 -> employeeMonthlyData1.getMonth() == employeeDataDto.getMonth()
                                && employeeMonthlyData1.getYear() == employeeDataDto.getYear()).collect(Collectors.toList());

        if (!employeeMonthlyDataList.isEmpty()) {
            throw new MonthAlreadyAddedException("Ten miesiąc i rok już został dodany");
        }

        return employeeDataRepo.createData(dtoToEmpData(employeeDataDto));
    }

    public EmployeeDto read(String pesel) throws IdNotFoundException {
        Employee employee = employeeRepo.read(pesel);
        List<EmployeeMonthlyData> employeeMonthlyData = this.employeeDataRepo.readData(pesel);
        return empToDto(employee, employeeMonthlyData);
    }

    public EmployeeDto update(String pesel, EmployeeDto employeeDto) throws IdNotFoundException {
        Employee updatedEmployee = dtoToEmp(employeeDto);
        List<EmployeeMonthlyData> employeeMonthlyDataList = this.employeeDataRepo.readData(pesel);
        this.employeeRepo.update(updatedEmployee);
        return empToDto(updatedEmployee, employeeMonthlyDataList);
    }

    public EmployeeMonthlyData updateData(String employeeId, EmployeeMonthlyData employeeMonthlyData) throws EmployeeMonthlyDataNotFound {
        List<EmployeeMonthlyData> employeeMonthlyDataList = employeeDataRepo.readData(employeeId);
        int index = employeeMonthlyDataList.indexOf(employeeMonthlyData);
        if (index == -1) {
            throw new EmployeeMonthlyDataNotFound();
        }
        return employeeMonthlyData;
    }

    public List<EmployeeDto> getAll() {
        return employeeRepo.getAll()
                .stream()
                .map(employee -> empToDto(employee, employeeDataRepo.readData(employee.getId())))
                .collect(Collectors.toList());
    }

    public boolean delete(String pesel) throws IdNotFoundException, MonthNotFoundException {
        this.employeeDataRepo.deleteData(pesel);
        return employeeRepo.delete(pesel);
    }

    public boolean isEmployeeIdAlreadyExist(String employeeId) {
        return employeeDataRepo.isEmployeeIdAlreadyExist(employeeId);
    }
}


