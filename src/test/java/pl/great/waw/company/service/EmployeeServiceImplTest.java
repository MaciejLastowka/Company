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
import pl.great.waw.company.exceptions.MonthNotFoundException;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.exceptions.PeselNotFoundException;
import pl.great.waw.company.model.Employee;
import pl.great.waw.company.repository.EmployeeDataRepo;
import pl.great.waw.company.repository.EmployeeRepository;

import java.math.BigDecimal;
import static pl.great.waw.company.Mapper.MapperEmployee.empToDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    private static final String FIRST_NAME_TEST = "TEST_NAME";
    private static final String LAST_NAME_TEST = "TEST_LAST_NAME";
    private static final String PESEL_TEST = "TEST_PESEL";
    private static final BigDecimal SALARY_TEST = BigDecimal.TEN;

    private static final String FIRST_NAME_TEST_UPDATED = "TEST-New_name";

    @Mock
    EmployeeRepository employeeRepo;
    @Mock
    EmployeeDataRepo employeeDataRepo;
    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl;

    private Employee employeeFromRepo;

    private EmployeeDto employeeFromController;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @BeforeEach
    public void setup() {
        this.employeeFromRepo = new Employee(PESEL_TEST, FIRST_NAME_TEST, LAST_NAME_TEST, SALARY_TEST);
        this.employeeFromController = new EmployeeDto(PESEL_TEST, FIRST_NAME_TEST, LAST_NAME_TEST, SALARY_TEST, null);
    }

    @Test
    void create() throws PeselAlreadyExistException {
        //given
        when(employeeRepo.create(any())).thenReturn(this.employeeFromRepo);
        //when
        EmployeeDto employeefromService = employeeServiceImpl.create(employeeFromController);
        //then
        assertEquals(PESEL_TEST, employeefromService.getPesel());
        assertEquals(employeefromService, employeeServiceImpl.create(employeefromService));
    }

    @Test
    void read() throws PeselNotFoundException {
        //given
        EmployeeDto employeeDto = new EmployeeDto("29123123", "bartek", "porebski", BigDecimal.TEN, null);
        when(employeeRepo.read(any())).thenReturn(new Employee("29123123", "bartek", "porebski", BigDecimal.TEN));
        //when
        EmployeeDto read = employeeServiceImpl.read("233321");
        verify(employeeRepo, times(1)).read(any());
        //then
        assertEquals(read.getPesel(), employeeDto.getPesel());
    }

//    @Test
//    void update() throws PeselNotFoundException {
//        //given
//        EmployeeDto employeeDto = new EmployeeDto(PESEL_TEST, FIRST_NAME_TEST_UPDATED, LAST_NAME_TEST, BigDecimal.TEN, null);
//        Employee employee = new Employee(PESEL_TEST, FIRST_NAME_TEST, LAST_NAME_TEST, BigDecimal.TEN);
//        when(employeeRepo.update(any(), any())).thenReturn(employee);
//        //when
//        EmployeeDto update = employeeServiceImpl.update(PESEL_TEST, employeeDto);
//        //then
//        assertNotEquals(employeeDto.getFirstName(), update.getFirstName());
//        assertEquals(employeeDto.getLastName(), update.getLastName());
//        assertEquals(employeeDto.getPesel(), update.getPesel());
//        assertEquals(employeeDto.getSalary(), employeeDto.getSalary());
//    }

    @Test
    void delete() throws PeselNotFoundException, MonthNotFoundException {
        //given
        when(employeeRepo.delete("29123123")).thenReturn(true);
        //when
        boolean delete = employeeServiceImpl.delete("29123123");
        //then
        verify(employeeRepo).delete("29123123");
    }

}