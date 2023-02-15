package pl.great.waw.company.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.great.waw.company.exceptions.MonthAlreadyAddedException;
import pl.great.waw.company.exceptions.MonthNotFoundException;
import pl.great.waw.company.model.EmployeeMonthlyData;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EmployeeDataRepositoryTest {

    private EmployeeDataRepo employeeDataRepo;
    private EmployeeMonthlyData employeeMonthlyData;

    private static final int TEST_DATA_COUNT = 12;

    @BeforeEach
    void setUpData() {
        this.employeeDataRepo = new EmployeeDataRepo();
        this.employeeMonthlyData = new EmployeeMonthlyData("11111", "123",
                1, BigDecimal.TEN, 2023);
    }

    @Test
    void testCreateData() throws MonthAlreadyAddedException {
        //when
        EmployeeMonthlyData createdData = employeeDataRepo.createData(employeeMonthlyData);
        //then
        assertEquals(employeeMonthlyData, createdData);
    }

    @Test
    void testReadData() throws MonthAlreadyAddedException, MonthNotFoundException {
        //given
        EmployeeMonthlyData createdData = employeeDataRepo.createData(employeeMonthlyData);
        //when
        List<EmployeeMonthlyData> employeeMonthlyData = employeeDataRepo.readData(createdData.getEmployeeId());
        //then
        assertEquals(this.employeeMonthlyData, employeeMonthlyData.get(0));
    }

    @Test
    void testUpdateData() throws MonthAlreadyAddedException, MonthNotFoundException {
        //given
        EmployeeMonthlyData createdData = employeeDataRepo.createData(employeeMonthlyData);
        EmployeeMonthlyData newData = new EmployeeMonthlyData(createdData.getId(), createdData.getEmployeeId(),
                createdData.getMonth(), BigDecimal.valueOf(1500), createdData.getYear());
        //when
        EmployeeMonthlyData updatedData = employeeDataRepo.updateData(createdData.getEmployeeId(), newData);
        //then
        assertEquals(newData, updatedData);
    }

    @Test
    void isEmployeeIdAlreadyExist() {
        assertFalse(employeeDataRepo.isEmployeeIdAlreadyExist("11111"));
    }
}