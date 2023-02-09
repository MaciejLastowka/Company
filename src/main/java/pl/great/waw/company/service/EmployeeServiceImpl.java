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

    public EmployeeMonthlyData createData(EmployeeDataDto employeeDataDto) throws MonthAlreadyAddedException, PeselNotFoundException, MonthNotFoundException {

        this.employeeRepo.read(employeeDataDto.getEmployeeId());

        List<EmployeeMonthlyData> employeeMonthlyDataListFromDb = this.employeeDataRepo.readData(employeeDataDto.getEmployeeId());

        List<EmployeeMonthlyData> employeeMonthlyDataList = employeeMonthlyDataListFromDb.stream()
                .filter(
                        employeeMonthlyData1 -> employeeMonthlyData1.getMonth() == employeeDataDto.getMonth()
                                && employeeMonthlyData1.getYear() == employeeDataDto.getYear()).collect(Collectors.toList());

        if(!employeeMonthlyDataList.isEmpty()){
            throw new MonthAlreadyAddedException("Ten miesiąc i rok już został dodany");
        }

        return employeeDataRepo.createData(dtoToEmpData(employeeDataDto));
    }

    public EmployeeDto read(String pesel) throws PeselNotFoundException {
        Employee employee = employeeRepo.read(pesel);
        List<EmployeeMonthlyData> employeeMonthlyData = this.employeeDataRepo.readData(pesel);
        return empToDto(employee, employeeMonthlyData);
    }

//    public EmployeeDto update(String pesel, EmployeeDto employeeDto) throws PeselNotFoundException {
//        Employee employee = dtoToEmp(employeeDto);
//        Employee update = employeeRepo.update(pesel, employee);
//        return empToDto(update);
//    }

    public List<EmployeeDto> getAll() {
        return employeeRepo.getAll()
                .stream()
                .map(employee -> empToDto(employee, employeeDataRepo.readData(employee.getPesel())))
                .collect(Collectors.toList());
    }

    public boolean delete(String pesel) throws PeselNotFoundException {
        return employeeRepo.delete(pesel);
    }

    public boolean isPeselAlreadyExist(String pesel) {
        return employeeRepo.isPeselAlreadyExist(pesel);
    }

    public boolean isEmployeeIdAlreadyExist(String employeeId) {
        return employeeDataRepo.isEmployeeIdAlreadyExist(employeeId);
    }
}


