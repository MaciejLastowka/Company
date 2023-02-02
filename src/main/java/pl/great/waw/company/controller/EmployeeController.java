package pl.great.waw.company.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import pl.great.waw.company.Json.JsonParsing;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.exceptions.PeselNotFoundException;
import pl.great.waw.company.model.Employee;
import pl.great.waw.company.service.EmployeeDto;
import pl.great.waw.company.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.any;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    private List<Employee> employeeList = new ArrayList<>();


    public EmployeeController( EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "{pesel}")
    public EmployeeDto get(@PathVariable String pesel) throws PeselNotFoundException {
        return employeeService.read(pesel);
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

        Employee employee = new Employee(employeeDto.getPesel(), employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getSalary());
        employeeList.add(employee);

        return employeeDto;
    }

    @DeleteMapping(value = "{pesel}")
    public boolean delete(@PathVariable String pesel) throws PeselNotFoundException {
        boolean isDeleted = employeeService.delete(pesel);

        if(isDeleted){
            employeeList.removeIf(employee -> employee.getPesel().equals(pesel));
        }return isDeleted;
    }

    @PutMapping(value = "{pesel}")
    public EmployeeDto update(@PathVariable String pesel, @RequestBody EmployeeDto employeeDto) throws PeselNotFoundException {
        EmployeeDto updatedEmployee= employeeService.update(pesel, employeeDto);
return null;
//        if(updatedEmployee != null){ employeeList.stream().filter(employee -> employee.getPesel().equals(pesel))
//                .findFirst()
//                .ifPresent(employee -> {
//        employee.getUpdated(employeeDto.getFirstName());
//        employee.getUpdated(employeeDto.getLastName());
//        employee.getUpdated(employeeDto.getSalary());
//                });
 //       }return updatedEmployee;
    }


}
