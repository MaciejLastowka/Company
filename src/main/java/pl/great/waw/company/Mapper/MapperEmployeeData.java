package pl.great.waw.company.Mapper;

import pl.great.waw.company.model.EmployeeMonthlyData;
import pl.great.waw.company.service.EmployeeDataDto;

public class MapperEmployeeData {
    public static EmployeeDataDto empDataToDto(EmployeeMonthlyData employeeMonthlyData) {
        EmployeeDataDto employeeDataDto = new EmployeeDataDto();
        employeeDataDto.setEmployeeId(employeeMonthlyData.getEmployeeId());
        employeeDataDto.setId(employeeMonthlyData.getId());
        employeeDataDto.setMonth(employeeMonthlyData.getMonth());
        employeeDataDto.setMonthlySalary(employeeMonthlyData.getMonthlySalary());
        employeeDataDto.setYear(employeeMonthlyData.getYear());
        return employeeDataDto;
    }

    public static EmployeeMonthlyData dtoToEmpData(EmployeeDataDto employeeDataDto) {
        return new EmployeeMonthlyData(employeeDataDto.getEmployeeId(), employeeDataDto.getId(),
                employeeDataDto.getMonth(), employeeDataDto.getMonthlySalary(), employeeDataDto.getYear());
    }
}
