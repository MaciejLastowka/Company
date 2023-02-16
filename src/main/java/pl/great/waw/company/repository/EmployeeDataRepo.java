package pl.great.waw.company.repository;

import org.springframework.stereotype.Repository;
import pl.great.waw.company.exceptions.MonthAlreadyAddedException;
import pl.great.waw.company.exceptions.MonthNotFoundException;
import pl.great.waw.company.model.Employee;
import pl.great.waw.company.model.EmployeeMonthlyData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EmployeeDataRepo {
    private final List<EmployeeMonthlyData> employeeMonthlyDataList = new ArrayList<>();
    private final int maxMonthsPerYear = 12;
    private EmployeeDataRepo employeeDataRepo;

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

    public EmployeeMonthlyData updateData(String employeeId, EmployeeMonthlyData employeeMonthlyData) throws MonthNotFoundException {
        List<EmployeeMonthlyData> employeeMonthlyDataList = this.employeeDataRepo.readData(employeeId);
        Optional<EmployeeMonthlyData> oldData = employeeMonthlyDataList.stream()
                .filter(data -> {
                    return Objects.equals(data.getMonth(), employeeMonthlyData.getMonth());
                })
                .findFirst();
        int index = employeeMonthlyDataList.indexOf(oldData);
        employeeMonthlyDataList.set(index, employeeMonthlyData);
        return employeeMonthlyData;
    }


    public boolean deleteData(String employeeId) throws MonthNotFoundException {
        return employeeMonthlyDataList.remove(this.readData(employeeId));
    }

    public int sizeData() {
        return employeeMonthlyDataList.size();
    }

    public List<EmployeeMonthlyData> getAll() {
        return new ArrayList<>(employeeMonthlyDataList);
    }
}
