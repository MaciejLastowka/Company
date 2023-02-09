package pl.great.waw.company.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.great.waw.company.exceptions.MonthAlreadyAddedException;
import pl.great.waw.company.exceptions.MonthNotFoundException;
import pl.great.waw.company.model.EmployeeMonthlyData;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EmployeeDataRepositoryTest {

    EmployeeDataRepo employeeDataRepo;
    private EmployeeMonthlyData employeeMonthlyData;
    private static final int TEST_DATA_COUNT = 12;

    @BeforeEach
    void setUpData() {
        this.employeeDataRepo = new EmployeeDataRepo();
        this.employeeMonthlyData = new EmployeeMonthlyData("11111", "123",
                1, BigDecimal.TEN, 2023);
    }

    @Test
    public void testCreateData() throws MonthAlreadyAddedException {
        //when
        EmployeeMonthlyData createdData = employeeDataRepo.createData(employeeMonthlyData);
        //then
        assertEquals(employeeMonthlyData, createdData);
    }

    @Test
    public void testReadData() throws MonthAlreadyAddedException, MonthNotFoundException {
        //given
        EmployeeMonthlyData createdData = employeeDataRepo.createData(employeeMonthlyData);
        //when
        EmployeeMonthlyData readData = employeeDataRepo.readData(createdData.getEmployeeId(), 2023, 1);
        //then
        assertEquals(employeeMonthlyData, readData);
    }

    @Test
    void isEmployeeIdAlreadyExist() {
        assertFalse(employeeDataRepo.isEmployeeIdAlreadyExist("11111"));
    }
}