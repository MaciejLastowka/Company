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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.great.waw.company.repository.EmployeeRepository;
import pl.great.waw.company.service.EmployeeDataDto;
import pl.great.waw.company.service.EmployeeDto;
import pl.great.waw.company.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
    private static final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private EmployeeServiceImpl employeeService;
    @Autowired
    private MockMvc mockMvc;

    public EmployeeControllerTest() throws Exception {
    }

    int TEST_DATA_COUNT = 100;

    @Test
    void testPost() throws Exception {
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
    void testPostData() throws Exception {
        Faker faker = new Faker(new Locale("pl"));
        for (int i = 0; i < TEST_DATA_COUNT; i++) {
            String id = String.valueOf(i);
            String employeeId = String.valueOf(100 + i);
            int month = faker.number().numberBetween(1,12);
            BigDecimal monthlySalary = new BigDecimal(faker.number().randomNumber());
            int year = faker.number().numberBetween(2016,2023);
            EmployeeDataDto employeeDataDto = new EmployeeDataDto(id,employeeId, month, monthlySalary, year);
            when(employeeService.create(ArgumentMatchers.any())).thenReturn(employeeDataDto);
            String json = mapper.writeValueAsString(employeeDataDto);
            mockMvc.perform(post("/employee")
                            .contentType(MediaType.APPLICATION_JSON)
                            .characterEncoding("utf-8").content(json)
                            .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                    .andExpect(jsonPath("$.pesel", Matchers.equalTo(String.valueOf(100 + i))));
        }
    }

    @Test
    void testGet() throws Exception {
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
        EmployeeDto employeeDto = mapper.readValue(mvcResult.getResponse().getContentAsString(), EmployeeDto.class);
        assertEquals(expectedEmployee, employeeDto);
    }

    @Test
    void updateTest() throws Exception {
        //given
        EmployeeDto employee = new EmployeeDto("123", "AAA", "bbb", BigDecimal.TEN);
        EmployeeDto updatedEmployee = new EmployeeDto("123", "NewName", "NewLastName", BigDecimal.TEN);
        Mockito.when(employeeService.update("123", updatedEmployee)).thenReturn(updatedEmployee);
        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/employee/123").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updatedEmployee)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$.pesel", Matchers.is("123")))
                .andReturn();
        //then
        EmployeeDto actualEmployee = mapper.readValue(result.getResponse().getContentAsString(), EmployeeDto.class);
        assertNotEquals(employee, updatedEmployee);
        assertEquals(updatedEmployee, actualEmployee);
    }

    @Test
    void delete1() throws Exception {
        //given
        EmployeeDto employee = new EmployeeDto("123", "AAA", "bbb", BigDecimal.TEN);
        when(employeeService.read(any())).thenReturn(employee);
        //when
        mockMvc.perform(delete("/employee/123"))
                .andExpect(status().isOk());
        //then
        verify(employeeService, times(1)).delete("123");
    }
}


