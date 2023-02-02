package pl.great.waw.company.hooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.repository.EmployeeRepository;
import pl.great.waw.company.repository.FilePersistanceUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Component
public class PersistanceHooks {

    @Autowired
    private FilePersistanceUtil filePersistanceUtil;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostConstruct
    void onInit() throws IOException {
        filePersistanceUtil.saveDataToFile("employee_list.json", employeeRepository.getAll());
    }

    @PreDestroy
    void onClose() throws IOException, PeselAlreadyExistException {
        filePersistanceUtil.loadDataFromFile();
    }
}
