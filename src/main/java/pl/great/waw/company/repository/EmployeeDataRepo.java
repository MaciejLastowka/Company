package pl.great.waw.company.repository;

import org.springframework.stereotype.Repository;
import pl.great.waw.company.exceptions.MonthAlreadyAddedException;
import pl.great.waw.company.exceptions.MonthNotFoundException;
import pl.great.waw.company.model.EmployeeMonthlyData;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDataRepo {
    private final List<EmployeeMonthlyData> employeeMonthlyDataList = new ArrayList<>();
    private final int maxMonthsPerYear = 12;

    public  EmployeeMonthlyData createData(EmployeeMonthlyData employeeMonthlyData) throws MonthAlreadyAddedException {
        int currentYearMonths = (int) employeeMonthlyDataList.stream()
                .filter(data -> data.getEmployeeId().equals(employeeMonthlyData.getEmployeeId()))
                .filter(data -> data.getYear() == employeeMonthlyData.getYear())
                .count();

        if (currentYearMonths >= maxMonthsPerYear) {
            throw new MonthAlreadyAddedException("Already added 12 months for employee " + employeeMonthlyData.getEmployeeId() + " in year " + employeeMonthlyData.getYear());
        }
        employeeMonthlyDataList.add(employeeMonthlyData);
        return employeeMonthlyData;
    }

    public boolean isEmployeeIdAlreadyExist(String employeeId) {
        return employeeMonthlyDataList.stream().anyMatch(data -> data.getEmployeeId().equals(employeeId));
    }

    public EmployeeMonthlyData readData(String employeeId, int year, int month) throws MonthNotFoundException {
        for (EmployeeMonthlyData employeeMonthlyData : employeeMonthlyDataList) {
            if (employeeMonthlyData.getEmployeeId().equals(employeeId) && employeeMonthlyData.getYear() == year && employeeMonthlyData.getMonth() == month) {
                return employeeMonthlyData;
            }
        }
        throw new MonthNotFoundException("Dane podanego miesiąca nie zostały znalezione: " + employeeId + " " + year + " " + month);
    }

    public EmployeeMonthlyData updateData(String employeeId, int year, int month, EmployeeMonthlyData employeeMonthlyData) throws MonthNotFoundException {
        EmployeeMonthlyData oldData = this.readData(employeeId, year, month);
        int index = employeeMonthlyDataList.indexOf(oldData);
        employeeMonthlyDataList.set(index, employeeMonthlyData);
        return employeeMonthlyData;
    }

    public boolean deleteData(String employeeId, int year, int month) throws MonthNotFoundException {
        return employeeMonthlyDataList.remove(this.readData(employeeId, year, month));
    }

    public int sizeData() {
        return employeeMonthlyDataList.size();
    }
}
