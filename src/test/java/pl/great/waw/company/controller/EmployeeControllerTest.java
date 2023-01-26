package pl.great.waw.company.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import pl.great.waw.company.model.Employee;
import pl.great.waw.company.repository.EmployeeRepository;
import pl.great.waw.company.service.EmployeeDto;
import pl.great.waw.company.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)

public class EmployeeControllerTest {
    @MockBean
    private EmployeeServiceImpl employeeService;
    private static final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    EmployeeRepository employeeRepo;

    public EmployeeControllerTest() throws Exception {
    }

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    int TEST_DATA_COUNT = 100;

    @Test
    public void testPostExample() throws Exception {
        Faker faker = new Faker(new Locale("pl"));
        for (int i = 0; i < TEST_DATA_COUNT; i++) {
            String pesel = String.valueOf(100 + i);
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            BigDecimal salary = new BigDecimal(faker.number().randomNumber());
            EmployeeDto employeeDto = new EmployeeDto(pesel, firstName, lastName, salary);
            when(employeeService.create(ArgumentMatchers.any())).thenReturn(employeeDto);
            String json = mapper.writeValueAsString(employeeDto);
            mockMvc.perform(post("/employee")
                            .contentType(MediaType.APPLICATION_JSON)
                            .characterEncoding("utf-8").content(json)
                            .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                    .andExpect(jsonPath("$.pesel", Matchers.equalTo(String.valueOf(100 + i))));
        }
    }

    @Test
    public void testGet() throws Exception {
        //given
        EmployeeDto expectedEmployee = new EmployeeDto("123", "AAA", "bbb", BigDecimal.TEN);
        when(employeeService.read(any())).thenReturn(expectedEmployee);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/employee/123"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$.pesel", Matchers.is("123")))
                .andReturn();
        //then
        Employee actualEmployee = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Employee.class);
        //assertEquals(expectedEmployee, actualEmployee));
    }

    @Test
    public void updateTest() throws Exception {
        //given
        EmployeeDto employee = new EmployeeDto("123", "AAA", "bbb", BigDecimal.TEN);
        EmployeeDto updatedEmployee = new EmployeeDto("123", "NewName", "NewLastName", BigDecimal.TEN);
        Mockito.when(employeeService.update("123", updatedEmployee)).thenReturn(updatedEmployee);
        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/employee/123").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$.pesel", Matchers.is("123")))
                //.andExpect(jsonPath("$.name", Matchers.is("BBB")))
                .andReturn();
        //then
        EmployeeDto actualEmployee = objectMapper.readValue(result.getResponse().getContentAsString(), EmployeeDto.class);
        assertNotEquals(employee, updatedEmployee);
         assertEquals("123", employeeService);
    }

    @Test
    public void delete1() throws Exception {
        //given
        EmployeeDto employee = new EmployeeDto("123", "AAA", "bbb", BigDecimal.TEN);
        when(employeeService.read(any())).thenReturn(employee);
        //when
        ResultActions resultActions = mockMvc.perform(delete("/employee/123"))
                .andExpect(status().isOk());
        //then
        assertNull(employeeService);
        verify(employeeService, times(1)).delete("123");
    }
}


