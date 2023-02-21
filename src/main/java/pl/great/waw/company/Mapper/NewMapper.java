package pl.great.waw.company.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pl.great.waw.company.model.Employee;
import pl.great.waw.company.model.EmployeeMonthlyData;
import pl.great.waw.company.service.EmployeeDataDto;
import pl.great.waw.company.service.EmployeeDto;

import java.util.List;

@Mapper
public interface NewMapper {

    @Mapping(source = "pesel", target = "pesel")
    @Mapping(source = "firstName", target = "firstName")
    EmployeeDto employeeToEmployeeDto(Employee employee, List<EmployeeMonthlyData>employeeMonthlyDataList);

    @Mapping(ignore = true, target = "created")
    @Mapping(ignore = true, target = "updated")
    void updateEmployeeFromEmployeeDto(EmployeeDto employeeDto, @MappingTarget Employee employee);


    @Mapping(source = "employeeId", target = "employeeId")
    @Mapping(source = "month", target = "month")
    @Mapping(source = "monthlySalary", target = "monthlySalary")
    void EmployeeDataFromEmployeeDataDto(EmployeeDataDto employeeDataDto, @MappingTarget EmployeeMonthlyData employeeMonthlyData);

    @Mapping(source = "employeeId", target = "employeeId")
    @Mapping(source = "month", target = "month")
    @Mapping(source = "monthlySalary", target = "monthlySalary")
    EmployeeMonthlyData employeeDataDtoToEmployeeData(EmployeeDataDto employeeDataDto);
}
