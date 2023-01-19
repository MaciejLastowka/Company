package pl.great.waw.company.services;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto that = (EmployeeDto) o;
        return Objects.equals(pesel, that.pesel) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel, firstName, lastName, price);
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "pesel='" + pesel + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", price=" + price +
                '}';
    }
}
