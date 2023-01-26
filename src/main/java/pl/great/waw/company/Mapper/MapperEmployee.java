package pl.great.waw.company.Mapper;

import pl.great.waw.company.model.Employee;
import pl.great.waw.company.service.EmployeeDto;


public class MapperEmployee {

    public static EmployeeDto empToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setPesel(employee.getPesel());
        employeeDto.setSalary(employee.getPrice());
        return employeeDto;
    }

    public static Employee dtoToEmp(EmployeeDto employeeDto) {
        return new Employee(employeeDto.getPesel(), employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getSalary());
    }
}
