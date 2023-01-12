package pl.great.waw.company.services;

import java.math.BigDecimal;

public class EmployeeDto {
    private String pesel;
    private String firstName;
    private String lastName;
    private BigDecimal price;


    public EmployeeDto() {

    }

    public EmployeeDto(String pesel, String firstName, String lastName, BigDecimal salary) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.price = salary;
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
}
