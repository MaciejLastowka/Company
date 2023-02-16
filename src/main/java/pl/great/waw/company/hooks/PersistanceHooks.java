package pl.great.waw.company.hooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.model.EmployeeMonthlyData;
import pl.great.waw.company.repository.EmployeeDataRepo;
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

    @Autowired
    private EmployeeDataRepo employeeDataRepo;

    @PostConstruct
    void onInit() throws IOException, PeselAlreadyExistException {
        filePersistanceUtil.loadDataFromFile("employee_list.json");
        filePersistanceUtil.loadDataFromFile("employee_monthly_data_list.json");
    }

    @PreDestroy
    void onClose() throws IOException {
        filePersistanceUtil.saveDataToFile("employee_list.json", employeeRepository.getAll());
        filePersistanceUtil.saveDataToFile("employee_monthly_data_list.json", employeeDataRepo.getAll());
    }
}
