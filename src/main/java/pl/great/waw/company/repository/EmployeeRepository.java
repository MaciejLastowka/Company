package pl.great.waw.company.repository;

import org.springframework.stereotype.Repository;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.exceptions.PeselNotFoundException;
import pl.great.waw.company.model.Employee;

import java.util.ArrayList;
import java.util.List;


@Repository
public class EmployeeRepository {

    private final List<Employee> list = new ArrayList<>();

    public Employee create(Employee employee) throws PeselAlreadyExistException {
        if (list.contains(employee)) {
            throw new PeselAlreadyExistException(("This pesel already exist: " + employee.getPesel()));
        }
        list.add(employee);
       // System.out.println("ADDED EMPLOYEE "+employee.toString());
        return employee;
    }

    public boolean isPeselAlreadyExist(String pesel) {
        for (Employee employee : list) {
            if (employee.getPesel().equals(pesel)) {
                return true;
            }
        }
        return false;
    }

    public List<Employee> getAll() {
        return new ArrayList<>(list);
    }

    public Employee read(String pesel) throws PeselNotFoundException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPesel().equals(pesel)) {
                return list.get(i);
            }
        }
        throw new PeselNotFoundException("This pesel not found: " + pesel);
    }

    public Employee update(String pesel, Employee employee) throws PeselNotFoundException {
        Employee oldEmployee = this.read(pesel);
        int index = list.indexOf(oldEmployee);
        list.set(index, employee);
        return employee;
    }

    public boolean delete(String pesel) throws PeselNotFoundException {
        return list.remove(this.read(pesel));
    }

    public int size() {
        return list.size();
    }
}
