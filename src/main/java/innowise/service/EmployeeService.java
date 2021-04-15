package innowise.service;

import innowise.entity.Employee;
import innowise.entity.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    Employee get(Long id);
    Employee add(EmployeeDto employeeDto);
    List<Employee> getAll();
}
