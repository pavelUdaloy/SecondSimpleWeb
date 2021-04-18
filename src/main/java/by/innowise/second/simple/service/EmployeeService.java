package by.innowise.second.simple.service;

import by.innowise.second.simple.entity.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto get(Long id);

    List<EmployeeDto> getAll();
}
