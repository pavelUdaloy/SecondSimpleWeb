package innowise.service;

import innowise.entity.Employee;
import innowise.entity.dto.EmployeeDto;
import innowise.mapper.EmployeeMapper;
import innowise.repository.EmployeeRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
//    @EntityGraph(value = "graph.Employee.cardAccounts")
    public Employee get(Long id) {
        return employeeRepository.get(id);
    }

    @Override
    public Employee add(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.convertDto(employeeDto);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }
}
