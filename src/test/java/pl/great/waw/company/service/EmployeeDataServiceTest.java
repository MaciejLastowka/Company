package pl.great.waw.company.service;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.great.waw.company.exceptions.MonthAlreadyAddedException;
import pl.great.waw.company.exceptions.MonthNotFoundException;
import pl.great.waw.company.exceptions.PeselNotFoundException;
import pl.great.waw.company.model.Employee;
import pl.great.waw.company.model.EmployeeMonthlyData;
import pl.great.waw.company.repository.EmployeeDataRepo;
import pl.great.waw.company.repository.EmployeeRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeDataServiceTest {

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

    @Test
    @BeforeEach
    public void setUpData() {
        this.employeeDataDto = new EmployeeDataDto();
        this.employeeMonthlyData = new EmployeeMonthlyData("11111", "123",
                1, BigDecimal.TEN, 2023);
    }

    @Test
     void createData() throws MonthAlreadyAddedException, MonthNotFoundException, PeselNotFoundException {
        //given
        when(employeeDataRepo.createData(any())).thenReturn(this.employeeMonthlyData);
        when(employeeRepository.read(any())).thenReturn(new Employee());
        //when
        EmployeeMonthlyData result = employeeServiceImpl.createData(employeeDataDto);
        //then
        assertEquals(employeeMonthlyData, result);
    }


}
