package pl.great.waw.company.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.exceptions.PeselNotFoundException;
import pl.great.waw.company.model.EmployeeData;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeDataRepoTest {
    private EmployeeDataRepo employeeDataRepo;
    private EmployeeData employeeData;
    private static final int TEST_DATA_COUNT = 10;


    @BeforeEach
    void setUp() {
        //given
        this.employeeDataRepo = new EmployeeDataRepo();
        this.employeeData = new EmployeeData("123", "1444", 6, BigDecimal.TEN, 2, 1992, "null");
    }


    @Test
    void createData() throws PeselAlreadyExistException {
        //when
        EmployeeData employeeData1 = employeeDataRepo.createData(employeeData);
        //then
        assertEquals(employeeData, employeeData1);
    }

    @Test
    void readData() throws PeselAlreadyExistException, PeselNotFoundException {
        //when
        employeeDataRepo.createData(employeeData);
        EmployeeData read = employeeDataRepo.readData("123");
        //then
        assertEquals(read, employeeData);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}