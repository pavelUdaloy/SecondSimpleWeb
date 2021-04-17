package by.innowise.second.simple.service;

import by.innowise.second.simple.entity.Employee;
import by.innowise.second.simple.entity.dto.EmployeeDto;
import by.innowise.second.simple.mapper.EmployeeMapper;
import by.innowise.second.simple.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto get(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            return employeeMapper.convertDao(employee);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public EmployeeDto add(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.convertDto(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.convertDao(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getAll() {
        List<Employee> employees = employeeRepository.getAllBy();
        return employeeMapper.convertDaos(employees);
    }
}
