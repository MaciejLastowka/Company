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
import pl.great.waw.company.exceptions.EmployeeMonthlyDataNotFound;
import pl.great.waw.company.exceptions.MonthAlreadyAddedException;
import pl.great.waw.company.exceptions.MonthNotFoundException;
import pl.great.waw.company.exceptions.PeselNotFoundException;
import pl.great.waw.company.model.EmployeeMonthlyData;
import pl.great.waw.company.repository.EmployeeDataRepo;
import pl.great.waw.company.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

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
        this.employeeDataDto = new EmployeeDataDto("11111", "123", 1, BigDecimal.TEN, 2023);
        this.employeeMonthlyData = new EmployeeMonthlyData("11111", "123",
                1, BigDecimal.TEN, 2023);
    }

    @Test
    void createData() throws MonthAlreadyAddedException, MonthNotFoundException, PeselNotFoundException {
        //given
        when(employeeDataRepo.createData(any())).thenReturn(employeeMonthlyData);
        //when
        EmployeeMonthlyData result = employeeServiceImpl.createData(new EmployeeDataDto("11111", "123", 1, BigDecimal.TEN, 2023));
        //then
        assertEquals(employeeMonthlyData, result);

    }

    @Test
    void readData() throws MonthAlreadyAddedException, MonthNotFoundException, PeselNotFoundException {
        //given

        when(employeeDataRepo.readData(employeeDataDto.getEmployeeId())).thenReturn(Collections.singletonList(this.employeeMonthlyData));
        //when
        List<EmployeeMonthlyData> result = (List<EmployeeMonthlyData>) this.employeeServiceImpl.read(employeeDataDto.getEmployeeId());
        //then
        assertEquals(employeeMonthlyData, result);
    }

    @Test
    void testUpdateData() throws MonthNotFoundException, EmployeeMonthlyDataNotFound {
        List<EmployeeMonthlyData> employeeMonthlyDataList = Collections.singletonList(new EmployeeMonthlyData(id, employeeId, month, monthlySalary, year));
        //when
        when(employeeDataRepo.readData(any())).thenReturn(employeeMonthlyDataList);
        //then
        EmployeeMonthlyData updated = employeeServiceImpl.updateData(employeeId, new EmployeeMonthlyData(id, null, 0, null, 0));

        assertEquals(updated,new EmployeeMonthlyData(id, null, 0, null, 0));
    }

}
