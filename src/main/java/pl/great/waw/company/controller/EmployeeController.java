package pl.great.waw.company.controller;

import org.springframework.web.bind.annotation.*;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.repository.EmployeeRepository;
import pl.great.waw.company.service.EmployeeDto;
import pl.great.waw.company.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository = new EmployeeRepository();
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeRepository);

    @GetMapping(value = "{pesel}")
    public EmployeeDto get(@PathVariable String pesel) throws PeselAlreadyExistException {
        return employeeService.read(pesel);
    }

    @GetMapping(value = "/isPeselAlreadyExist/{pesel}")
    public boolean isPeselAlreadyExist(@PathVariable String pesel) throws PeselAlreadyExistException {
        return employeeService.isPeselAlreadyExist(pesel);
    }

    @PostMapping
    public EmployeeDto create(@RequestBody EmployeeDto employeeDto) throws PeselAlreadyExistException {
        if (employeeService.isPeselAlreadyExist(employeeDto.getPesel())) {
            throw new PeselAlreadyExistException("istnieje");
        }
        employeeService.create(employeeDto);
        return employeeDto;
    }

    @DeleteMapping(value = "{pesel}")
    public boolean delete(@PathVariable String pesel) throws PeselAlreadyExistException {
        return employeeService.delete(pesel);
    }

    @PutMapping(value = "{pesel}")
    public EmployeeDto update(@PathVariable String pesel, @RequestBody EmployeeDto employeeDto) throws PeselAlreadyExistException {
        return employeeService.update(pesel, employeeDto);
    }


}
