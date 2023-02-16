package pl.great.waw.company.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class EmployeeMonthlyData implements Serializable {
    private final String id;
    private final String employeeId;
    private final int month;
    private final BigDecimal monthlySalary;
    private final int year;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeMonthlyData that = (EmployeeMonthlyData) o;
        return that.id.equals(((EmployeeMonthlyData) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, month, monthlySalary, year);
    }
}


