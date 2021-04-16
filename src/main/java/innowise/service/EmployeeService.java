package innowise.service;

import innowise.entity.Employee;
import innowise.entity.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto get(Long id);
    EmployeeDto add(EmployeeDto employeeDto);
    List<EmployeeDto> getAll();
    List<EmployeeDto> getAllWithRoles();
}
