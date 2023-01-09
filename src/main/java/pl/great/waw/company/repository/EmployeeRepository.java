package pl.great.waw.company.repository;

import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.model.Employee;

import java.util.ArrayList;
import java.util.List;


public class EmployeeRepository {
    private final List<Employee> list = new ArrayList<>();

    public Employee create(Employee employee) throws PeselAlreadyExistException {
        if (list.contains(employee)) {
            throw new PeselAlreadyExistException(("This pesel already exist: " + employee.getPesel()));
        }
        list.add(employee);
        return employee;
    }

    public Employee read(String pesel) throws PeselAlreadyExistException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPesel().equals(pesel)) {
                return list.get(i);
            }
        }
        throw new PeselAlreadyExistException("This pesel not found: " + pesel);
    }

    public Employee update(String pesel, Employee employee) throws PeselAlreadyExistException {
        Employee oldEmployee = this.read(pesel);
        int index = list.indexOf(oldEmployee);
        list.set(index, employee);
        return employee;
    }

    public boolean delete(String pesel) throws PeselAlreadyExistException {
        return list.remove(this.read(pesel));
    }

    public int size() {
        return list.size();
    }


}
