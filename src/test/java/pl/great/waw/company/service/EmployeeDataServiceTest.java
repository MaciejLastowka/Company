package pl.great.waw.company.service;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.great.waw.company.exceptions.*;
import pl.great.waw.company.model.Employee;
import pl.great.waw.company.model.EmployeeMonthlyData;
import pl.great.waw.company.repository.EmployeeDataRepo;
import pl.great.waw.company.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeDataServiceTest {

    private static final String id = "11111";
    private static final String employeeId = "123";
    private static final int month = 1;
    private static final BigDecimal monthlySalary = BigDecimal.TEN;
    private static final int year = 2023;

    @Mock
    EmployeeDataRepo employeeDataRepo;
    @Mock
    EmployeeRepository employeeRepository;
    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl;

    private EmployeeDataDto employeeDataDto;

    private EmployeeMonthlyData employeeMonthlyData;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    private EmployeeMonthlyData employeeMonthlyData2;

    @BeforeEach
     void setUpData() {
        this.employeeDataDto = new EmployeeDataDto("11111", "123", 1, BigDecimal.TEN, 2023);
        this.employeeMonthlyData = new EmployeeMonthlyData("11111", "123", 1, BigDecimal.TEN, 2023);
        this.employeeMonthlyData2 = new EmployeeMonthlyData("11111", "123", 2, BigDecimal.TEN, 2023);
    }

    @Test
    void createData() throws MonthAlreadyAddedException, MonthNotFoundException, IdNotFoundException {
        //given
        when(employeeDataRepo.createData(any())).thenReturn(employeeMonthlyData);
        //when
        EmployeeMonthlyData result = employeeServiceImpl.createData(new EmployeeDataDto("11111", "123", 1, BigDecimal.TEN, 2023));
        //then
        assertEquals(employeeMonthlyData, result);

    }

    @Test
    void readData() {
        //given
        when(employeeDataRepo.readData(employeeDataDto.getEmployeeId())).thenReturn(Collections.singletonList(this.employeeMonthlyData));
        List<EmployeeMonthlyData> required = new ArrayList<>();
        required.add(employeeMonthlyData);
        //when
        List<EmployeeMonthlyData> provided = employeeDataRepo.readData("123");
        //then
        assertEquals(required, provided);
    }

    @Test
    void testUpdateData() throws EmployeeMonthlyDataNotFound {
        //given
        List<EmployeeMonthlyData> employeeMonthlyDataList = Collections.singletonList(new EmployeeMonthlyData(id, employeeId, month, monthlySalary, year));
        when(employeeDataRepo.readData(any())).thenReturn(employeeMonthlyDataList);
        //when
        EmployeeMonthlyData updated = employeeServiceImpl.updateData(employeeId, new EmployeeMonthlyData(id, null, 0, null, 0));
        //then
        assertEquals(updated, new EmployeeMonthlyData(id, null, 0, null, 0));
    }

    @Test
    void readMonthlySalary() throws IdNotFoundException {
        Employee employee = new Employee("29123123", "bartek", "porebski", BigDecimal.TEN);
        when(employeeRepository.read(any())).thenReturn(employee);
        assertEquals(employee.getPrice().toString(), employeeServiceImpl.readMonthlySalary("29123123"));
    }

    @Test
    void readYearlySalary() throws IdNotFoundException {
        //given
        Employee employee = new Employee("123", "bartek", "porebski", BigDecimal.TEN);
        when(employeeRepository.read(any())).thenReturn(employee);
        //when
        String result = employeeServiceImpl.readYearlySalary("123");
        //then
        assertEquals("120", result);
    }

    @Test
    void readTotalSalary() {
        //given
        when(employeeDataRepo.readData(employeeDataDto.getEmployeeId()))
                .thenReturn(Arrays.asList(this.employeeMonthlyData,employeeMonthlyData2));
        //when
        String provided = employeeServiceImpl.readTotalSalary("123");
        //then
        assertEquals("20", provided);
    }

}
