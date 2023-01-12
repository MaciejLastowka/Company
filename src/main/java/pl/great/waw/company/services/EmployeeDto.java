package pl.great.waw.company.services;

import java.math.BigDecimal;

public class EmployeeDto {

    private String firstName;
    private String lastName;
    private String pesel;
    private BigDecimal price;


    public EmployeeDto() {

    }

    public EmployeeDto(String firstName, String lastName, String pesel, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
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
