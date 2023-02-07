package pl.great.waw.company.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EmployeeData {
    private String id;
    private String employeeId;
    private int month;
    private BigDecimal monthlySalary;
    private int year;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public EmployeeData(String id, String employeeId, int month, BigDecimal monthlySalary, int year, LocalDateTime createdAt, LocalDateTime updatedAt) {
    }

    public EmployeeData(String id, String employeeId, int month, BigDecimal monthlySalary, int year, int i, String aNull) {
        this.id = id;
        this.employeeId = employeeId;
        this.month = month;
        this.monthlySalary = monthlySalary;
        this.year = year;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "EmployeeData{" +
                "id = '" + id + '\'' +
                ", employeeId = '" + employeeId + '\'' +
                ", month = " + month +
                ", monthlySalary = " + monthlySalary +
                ", year = " + year +
                ", createdAt = " + createdAt +
                ", updatedAt = " + updatedAt +
                '}';
    }
}


