package pl.great.waw.company.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import pl.great.waw.company.service.EmployeeDto;
import pl.great.waw.company.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
            Mockito.when(employeeService.create(ArgumentMatchers.any())).thenReturn(employeeDto);
            String json = mapper.writeValueAsString(employeeDto);
            mockMvc.perform(post("/employee")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8").content(json)
                    .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                    .andExpect(jsonPath("$.pesel", Matchers.equalTo(String.valueOf(100 + i))));
        }
    }
}