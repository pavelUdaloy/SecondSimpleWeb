package innowise.service;

import innowise.entity.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto get(Long id);

    List<EmployeeDto> getAll();

    EmployeeDto add(EmployeeDto employeeDto);

    List<EmployeeDto> getAllWithRoles();
}
