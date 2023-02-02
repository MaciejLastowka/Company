package pl.great.waw.company.Json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.great.waw.company.model.Employee;

import java.util.List;


@Component
public class JsonParsing {

    @Autowired
    private  final ObjectMapper objectMapper;

    public JsonParsing(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Employee> deParseEmployee(String jsonString) throws JsonProcessingException {
        objectMapper.registerModule(new JSR310Module());
        return objectMapper.readValue(jsonString, new TypeReference<List<Employee>>() {
        });
    }

    public String parseEmployee(List<Employee> employeeList) throws JsonProcessingException {
        objectMapper.registerModule(new JSR310Module());

        return objectMapper.writeValueAsString(employeeList);
    }

}






