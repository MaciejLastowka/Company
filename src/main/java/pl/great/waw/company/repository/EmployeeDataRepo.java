package pl.great.waw.company.repository;

import org.springframework.stereotype.Repository;
import pl.great.waw.company.exceptions.MonthAlreadyAddedException;
import pl.great.waw.company.exceptions.MonthNotFoundException;
import pl.great.waw.company.model.EmployeeMonthlyData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeDataRepo {
    private final List<EmployeeMonthlyData> employeeMonthlyDataList = new ArrayList<>();
    private final int maxMonthsPerYear = 12;

    public EmployeeMonthlyData createData(EmployeeMonthlyData employeeMonthlyData) throws MonthAlreadyAddedException {

        employeeMonthlyDataList.add(employeeMonthlyData);
        return employeeMonthlyData;
    }

    public boolean isEmployeeIdAlreadyExist(String employeeId) {
        return employeeMonthlyDataList.stream().anyMatch(data -> data.getEmployeeId().equals(employeeId));
    }

    public List<EmployeeMonthlyData> readData(String employeeId) {
        return this.employeeMonthlyDataList.stream()
                .filter(employeeMonthlyData -> employeeMonthlyData.getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());
    }

//    public EmployeeMonthlyData updateData(String employeeId, int year, int month, EmployeeMonthlyData employeeMonthlyData) throws MonthNotFoundException {
//        EmployeeMonthlyData oldData = this.readData(employeeId, year, month);
//        int index = employeeMonthlyDataList.indexOf(oldData);
//        employeeMonthlyDataList.set(index, employeeMonthlyData);
//        return employeeMonthlyData;
//    }

//    public boolean deleteData(String employeeId, int year, int month) throws MonthNotFoundException {
//        return employeeMonthlyDataList.remove(this.readData(employeeId, year, month));
//    }

    public int sizeData() {
        return employeeMonthlyDataList.size();
    }
}
