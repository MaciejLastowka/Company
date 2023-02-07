package pl.great.waw.company.repository;

import org.springframework.stereotype.Repository;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.exceptions.PeselNotFoundException;
import pl.great.waw.company.model.EmployeeData;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDataRepo {

    private final List<EmployeeData> employeeDataList = new ArrayList<>();

    public EmployeeData createData(EmployeeData employeeData) throws PeselAlreadyExistException {
        if (employeeDataList.contains(employeeData)) {
            throw new PeselAlreadyExistException(("This pesel already exist: " + employeeData.getId()));
        }
        employeeDataList.add(employeeData);
        System.out.println("ADDED EMPLOYEE " + employeeData.toString());
        return employeeData;
    }

    public boolean isPeselAlreadyExist(String id) {
        for (EmployeeData employeeData : employeeDataList) {
            if (employeeData.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public EmployeeData readData(String id) throws PeselNotFoundException {
        for (EmployeeData employeeData : employeeDataList) {
            if (employeeData.getId().equals(id)) {
                return employeeData;
            }
        }
        throw new PeselNotFoundException("This ID not found: " + id);
    }

    public EmployeeData updateData(String id, EmployeeData employeeData) throws PeselNotFoundException {
        EmployeeData oldEmployeeData = this.readData(id);
        int index = employeeDataList.indexOf(oldEmployeeData);
        employeeDataList.set(index, employeeData);
        return employeeData;
    }

    public boolean deleteData(String id) throws PeselNotFoundException {
        return employeeDataList.remove(this.readData(id));
    }

    public int size() {
        return employeeDataList.size();
    }
}
