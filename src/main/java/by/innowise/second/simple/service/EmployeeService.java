package by.innowise.second.simple.service;

import by.innowise.second.simple.controller.dto.EmployeeDto;
import by.innowise.second.simple.controller.dto.FilterDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    EmployeeDto get(Long id);

    List<EmployeeDto> getAllWithPag(Pageable pageable, FilterDto filterDto);
}
