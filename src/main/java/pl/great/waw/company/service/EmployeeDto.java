package pl.great.waw.company.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class EmployeeDto {

    private String pesel;
    private String firstName;
    private String lastName;
    private BigDecimal price;
    private List<EmployeeDataDto> employeeDataDtoList;

    public EmployeeDto() {

    }

    public EmployeeDto(String pesel, String firstName, String lastName, BigDecimal salary, List<EmployeeDataDto> employeeMonthlyDataList) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.price = salary;
        this.employeeDataDtoList = employeeMonthlyDataList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public BigDecimal getSalary() {
        return price;
    }

    public void setSalary(BigDecimal salary) {
        this.price = salary;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<EmployeeDataDto> getEmployeeDataDtoList() {
        return employeeDataDtoList;
    }

    public void setEmployeeDataDtoList(List<EmployeeDataDto> employeeDataDtoList) {
        this.employeeDataDtoList = employeeDataDtoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto that = (EmployeeDto) o;
        return Objects.equals(pesel, that.pesel) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(price, that.price) && Objects.equals(employeeDataDtoList, that.employeeDataDtoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel, firstName, lastName, price, employeeDataDtoList);
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "pesel='" + pesel + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", price=" + price +
                ", employeeDataDtoList=" + employeeDataDtoList +
                '}';
    }
}
