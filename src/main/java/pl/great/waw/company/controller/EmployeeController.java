package pl.great.waw.company.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import pl.great.waw.company.exceptions.MonthAlreadyAddedException;
import pl.great.waw.company.exceptions.MonthNotFoundException;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.exceptions.PeselNotFoundException;
import pl.great.waw.company.service.EmployeeDataDto;
import pl.great.waw.company.service.EmployeeDto;
import pl.great.waw.company.service.EmployeeServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "{pesel}")
    public EmployeeDto get(@PathVariable String pesel) throws PeselNotFoundException {
        return employeeService.read(pesel);
    }

    @GetMapping(value = "{employeeId, month, year}")
    public EmployeeDataDto get(@PathVariable String employeeId, @PathVariable int month, @PathVariable int year) throws MonthNotFoundException {
        return employeeService.readData(employeeId, month, year);
    }

    @GetMapping
    public List<EmployeeDto> getAll() {
        return employeeService.getAll();
    }

    @PostMapping
    public EmployeeDto create(@RequestBody EmployeeDto employeeDto) throws PeselAlreadyExistException, JsonProcessingException {
        if (employeeService.isPeselAlreadyExist(employeeDto.getPesel())) {
            throw new PeselAlreadyExistException("Pesel already exist: " + employeeDto.getPesel());
        }
        employeeService.create(employeeDto);

        return employeeDto;
    }

    @PostMapping("/createData")
    public EmployeeDataDto createData(@RequestBody EmployeeDataDto employeeDataDto) throws PeselAlreadyExistException, MonthAlreadyAddedException {
        if (employeeService.isEmployeeIdAlreadyExist(employeeDataDto.getEmployeeId())) {
            throw new PeselAlreadyExistException("Pesel already exist: " + employeeDataDto.getEmployeeId());
        }
        employeeService.createData(employeeDataDto);

        return employeeDataDto;
    }

    @DeleteMapping(value = "{pesel}")
    public boolean delete(@PathVariable String pesel) throws PeselNotFoundException {
        return employeeService.delete(pesel);
    }

    @PutMapping(value = "{pesel}")
    public EmployeeDto update(@PathVariable String pesel, @RequestBody EmployeeDto employeeDto) throws PeselNotFoundException {
        return employeeService.update(pesel, employeeDto);
    }
}
