package pl.great.waw.company.repository;

import org.springframework.stereotype.Repository;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.model.Employee;

import java.util.List;


@Repository
public class EmployeeRepository extends AbstractRepo<Employee> {

}
