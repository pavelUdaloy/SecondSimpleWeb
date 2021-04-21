package by.innowise.second.simple.service;

import by.innowise.second.simple.controller.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto get(Long id);

    List<EmployeeDto> getAll();
}
