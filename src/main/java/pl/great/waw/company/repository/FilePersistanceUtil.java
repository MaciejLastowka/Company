package pl.great.waw.company.repository;

import org.apache.maven.surefire.shared.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.great.waw.company.Json.JsonParsing;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.model.Employee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class FilePersistanceUtil {

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final JsonParsing jsonParsing;

    public FilePersistanceUtil(EmployeeRepository employeeRepository, JsonParsing jsonParsing) {
        this.employeeRepository = employeeRepository;
        this.jsonParsing = jsonParsing;
    }

    public void loadDataFromFile() throws IOException, PeselAlreadyExistException {

        File file = new File("employee_list.json");
        if (!file.exists()) {
            return;
        }

        String jsonString = FileUtils.readFileToString(file, "UTF-8");
        List<Employee> employees = jsonParsing.deParseEmployee(jsonString);

        for (Employee employee : employees) {
            employeeRepository.create(employee);
        }
    }

    public void saveDataToFile(String pathToFile, List<Employee> employeeList) throws IOException {
        String jsonString = jsonParsing.parseEmployee(employeeList);

        try (FileWriter fileWriter = new FileWriter(pathToFile)) {
            fileWriter.write(jsonString);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }
}
