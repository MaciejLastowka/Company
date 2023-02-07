package pl.great.waw.company.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EmployeeDateDto {
    private String id;
    private String employeeId;
    private int month;
    private BigDecimal monthlySalary;
    private int year;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EmployeeDateDto(String id, String employeeId, int month, BigDecimal monthlySalary, int year, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.employeeId = employeeId;
        this.month = month;
        this.monthlySalary = monthlySalary;
        this.year = year;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
}
