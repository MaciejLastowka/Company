package pl.great.waw.company.service;

import java.math.BigDecimal;
import java.util.Objects;

public class EmployeeDataDto {
    private String id;
    private String employeeId;
    private int month;
    private BigDecimal monthlySalary;
    private int year;

    public EmployeeDataDto() {
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setMonthlySalary(BigDecimal monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public EmployeeDataDto(String id, String employeeId, int month, BigDecimal monthlySalary, int year) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDataDto that = (EmployeeDataDto) o;
        return month == that.month && year == that.year && Objects.equals(id, that.id) && Objects.equals(employeeId, that.employeeId) && Objects.equals(monthlySalary, that.monthlySalary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, month, monthlySalary, year);
    }
}
