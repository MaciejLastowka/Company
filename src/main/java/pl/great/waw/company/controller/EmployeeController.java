package pl.great.waw.company.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.great.waw.company.exceptions.*;
import pl.great.waw.company.model.EmployeeMonthlyData;
import pl.great.waw.company.service.EmployeeDataDto;
import pl.great.waw.company.service.EmployeeDto;
import pl.great.waw.company.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "{pesel}")
    public EmployeeDto get(@PathVariable String pesel) throws IdNotFoundException {
        return employeeService.read(pesel);
    }

    @GetMapping
    public List<EmployeeDto> getAll() {
        return employeeService.getAll();
    }

    @PostMapping
    public EmployeeDto create(@RequestBody EmployeeDto employeeDto) throws PeselAlreadyExistException {

        employeeDto.setEmployeeDataDtoList(new ArrayList<>());
        employeeService.create(employeeDto);

        return employeeDto;
    }

    @PostMapping("/createData")
    public EmployeeDataDto createData(@RequestBody EmployeeDataDto employeeDataDto) throws MonthNotFoundException, MonthAlreadyAddedException, IdNotFoundException {

        employeeService.createData(employeeDataDto);

        return employeeDataDto;
    }

    @PutMapping(value = "{pesel}")
    public EmployeeDto update(@PathVariable String pesel, @RequestBody EmployeeDto employeeDto) throws IdNotFoundException {
        return employeeService.update(pesel, employeeDto);
    }
    @PutMapping(value = "/{employeeId}/monthly-data")
    public EmployeeMonthlyData updateData(@PathVariable String employeeId, @RequestBody EmployeeMonthlyData employeeMonthlyData) throws MonthNotFoundException, EmployeeMonthlyDataNotFound {
        return employeeService.updateData(employeeId, employeeMonthlyData);
    }

    @DeleteMapping(value = "{pesel}")
    public boolean delete(@PathVariable String pesel) throws IdNotFoundException, MonthNotFoundException {
        return employeeService.delete(pesel);
    }

    @GetMapping(value = "/{employeeId}/monthly-salary")
    public BigDecimal getMonthlySalary(@PathVariable String employeeId) throws IdNotFoundException {
        EmployeeDto employeeDto = employeeService.read(employeeId);
        return employeeDto.getSalary();
    }

    @GetMapping(value = "/{employeeId}/yearly-salary")
    public BigDecimal getYearlySalary(@PathVariable String employeeId) throws IdNotFoundException {
        EmployeeDto employeeDto = employeeService.read(employeeId);
        BigDecimal monthlySalary = employeeDto.getSalary();
        return monthlySalary.multiply(BigDecimal.valueOf(12));

    }

    @GetMapping(value = "/{employeeId}/total-salary")
    public BigDecimal getTotalSalary(@PathVariable String employeeId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate) throws IdNotFoundException {
        EmployeeDto employeeDto = employeeService.read(employeeId);
        return employeeDto.getTotalSalary(employeeDto, startDate);

    }
}
