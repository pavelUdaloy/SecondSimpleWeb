package innowise.service;

import innowise.entity.Employee;
import innowise.entity.dto.EmployeeDto;
import innowise.mapper.EmployeeMapper;
import innowise.repository.EmployeeRepository;
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
    @Transactional
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
    @Transactional
    public List<EmployeeDto> getAll() {
        List<Employee> employees = employeeRepository.getAllBy();
        return employeeMapper.convertDaos(employees);
    }

    @Override
    @Transactional
    public List<EmployeeDto> getAllWithRoles() {
        List<Employee> employees = employeeRepository.findAllBy();
        return employeeMapper.convertDaos(employees);
    }
}
