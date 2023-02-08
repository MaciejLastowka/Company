package pl.great.waw.company.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Employee {

    private  String pesel;
    private  String firstName;
    private  String lastName;
    private  BigDecimal price;
    private  LocalDateTime created;
    private  LocalDateTime updated;

    public Employee() {
    }

    public Employee(String pesel, String firstName, String lastName, BigDecimal price, LocalDateTime created, LocalDateTime updated) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.price = price;
        this.created = created;
        this.updated = updated;
    }

    public Employee(String pesel, String firstName, String lastName, BigDecimal price) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.price = price;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();

    }


    public String getPesel() {
        return pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(pesel, employee.pesel) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(price, employee.price) && Objects.equals(created, employee.created) && Objects.equals(updated, employee.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel, firstName, lastName, price, created, updated);
    }
}
