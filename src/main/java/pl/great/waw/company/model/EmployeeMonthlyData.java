package pl.great.waw.company.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EmployeeMonthlyData {
    private String id;
    private String employeeId;
    private int month;
    private BigDecimal monthlySalary;
    private int year;


    public EmployeeMonthlyData(String id, String employeeId, int month, BigDecimal monthlySalary, int year) {
        this.id = id;
        this.employeeId = employeeId;
        this.month = month;
        this.monthlySalary = monthlySalary;
        this.year = year;
    }


    public String getId() {
        return id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public int getMonth() {
        return month;
    }

    public BigDecimal getMonthlySalary() {
        return monthlySalary;
    }

    public int getYear() {
        return year;
    }


    @Override
    public String toString() {
        return "EmployeeData{" +
                "id = '" + id + '\'' +
                ", employeeId = '" + employeeId + '\'' +
                ", month = " + month +
                ", monthlySalary = " + monthlySalary +
                ", year = " + year +
                '}';
    }
}


