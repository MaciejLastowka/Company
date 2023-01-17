package pl.great.waw.company.services;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.model.Employee;
import pl.great.waw.company.repository.EmployeeRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {


    private static final String FIRST_NAME_TEST = "TEST_NAME";
    private static final String LAST_NAME_TEST = "TEST_LAST_NAME";
    private static final String PESEL_TEST = "TEST_PESEL";
    private static final BigDecimal SALARY_TEST = BigDecimal.TEN;

    private static final String FIRST_NAME_TEST_UPDATED = "TEST-New_name";

    @Mock
    EmployeeRepository employeeRepo;

    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl;

    private Employee employeeFromRepo;

    private EmployeeDto employeeFromController;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @BeforeEach
    public void setup() throws Exception {
        this.employeeFromRepo = new Employee(PESEL_TEST, FIRST_NAME_TEST, LAST_NAME_TEST, SALARY_TEST);
        this.employeeFromController = new EmployeeDto(PESEL_TEST, FIRST_NAME_TEST, LAST_NAME_TEST, SALARY_TEST);
    }

    @Test
    void create() throws PeselAlreadyExistException {
        when(employeeRepo.create(any())).thenReturn(this.employeeFromRepo);
        EmployeeDto employeefromService = employeeServiceImpl.create(employeeFromController);
        assertEquals(PESEL_TEST, employeefromService.getPesel());
        assertEquals(employeefromService, employeeServiceImpl.create(employeefromService));
    }

    @Test
    void read() throws PeselAlreadyExistException {
        //given
        EmployeeDto employeeDto = new EmployeeDto("29123123", "bartek", "porebski", BigDecimal.TEN);
        when(employeeRepo.read(any())).thenReturn(new Employee("29123123", "bartek", "porebski", BigDecimal.TEN));
        //when
        EmployeeDto read = employeeServiceImpl.read("233321");
        //then
        assertEquals(read, employeeDto);
    }


    @Test
    void update() throws PeselAlreadyExistException {
        //given
        EmployeeDto employeeDto = new EmployeeDto(PESEL_TEST, FIRST_NAME_TEST_UPDATED, LAST_NAME_TEST, BigDecimal.TEN);
        Employee employee = new Employee(PESEL_TEST, FIRST_NAME_TEST, LAST_NAME_TEST, BigDecimal.TEN);
        when(employeeRepo.update(any(), any())).thenReturn(employee);
        //when
        EmployeeDto update = employeeServiceImpl.update(PESEL_TEST, employeeDto);
        //then
        assertNotEquals(employeeDto.getFirstName(), update.getFirstName());
        assertEquals(employeeDto.getLastName(), update.getLastName());

    }

    @Test
    void delete() throws PeselAlreadyExistException {
        //given
        when(employeeRepo.delete("29123123")).thenReturn(true);
        //when
        boolean delete = employeeServiceImpl.delete("29123123");
        //then
        verify(employeeRepo).delete("29123123");


    }

}