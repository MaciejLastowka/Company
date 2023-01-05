package pl.great.waw.company.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Employee {


    private final String pesel;
    private final String firstName;
    private final String lastName;
    private final BigDecimal price;
    private final LocalDateTime created;
    private final LocalDateTime updated;


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

}
