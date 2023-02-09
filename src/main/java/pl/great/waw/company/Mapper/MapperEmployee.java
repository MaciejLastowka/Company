package pl.great.waw.company.Mapper;

import pl.great.waw.company.model.Employee;
import pl.great.waw.company.model.EmployeeMonthlyData;
import pl.great.waw.company.service.EmployeeDto;

import java.util.List;
import java.util.stream.Collectors;


public class MapperEmployee {



    public static EmployeeDto empToDto(Employee employee, List<EmployeeMonthlyData> employeeMonthlyDataList) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setPesel(employee.getPesel());
        employeeDto.setSalary(employee.getPrice());
        employeeDto.setEmployeeDataDtoList(
                employeeMonthlyDataList.stream()
                        .map((MapperEmployeeData::empDataToDto))
                        .collect(Collectors.toList()));

        return employeeDto;
    }

    public static Employee dtoToEmp(EmployeeDto employeeDto) {
        return new Employee(employeeDto.getPesel(), employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getSalary());
    }

}
