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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {


    private static final String FIRST_NAME_TEST = "TEST_NAME";
    private static final String LAST_NAME_TEST = "TEST_LAST_NAME";
    private static final String PESEL_TEST = "TEST_PESEL";
    private static final BigDecimal SALARY_TEST = BigDecimal.TEN;

    @Mock
    EmployeeRepository employeeRepo;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    private Employee employeeFromRepo;

    private EmployeeDto employeeFromController;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @BeforeEach
    public void setup() throws Exception {
        this.employeeFromRepo = new Employee(PESEL_TEST, FIRST_NAME_TEST, LAST_NAME_TEST , SALARY_TEST);
        this.employeeFromController = new EmployeeDto(PESEL_TEST, FIRST_NAME_TEST, LAST_NAME_TEST, SALARY_TEST);
    }

    @Test
    void create() throws PeselAlreadyExistException {
        EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl(employeeRepo);
        when(employeeRepo.create(any())).thenReturn(this.employeeFromRepo);
        EmployeeDto employeefromService = employeeServiceImpl.create(employeeFromController);
        assertEquals(PESEL_TEST, employeefromService.getPesel());
//        assertEquals(employeefromService, employeeServiceImpl.create("22222222", "bartek", "porebski", BigDecimal.TEN));
    }

    @Test
    void read() throws PeselAlreadyExistException {
        EmployeeServiceImpl employeeServiceImpl = mock(EmployeeServiceImpl.class);
        EmployeeDto employeeDto = new EmployeeDto("29123123", "bartek", "porebski", BigDecimal.TEN);
        employeeServiceImpl.create(employeeDto);
//            assertEquals(employee,employee1);
    }


    @Test
    void update() {
    }

    @Test
    void delete() {
    }

}