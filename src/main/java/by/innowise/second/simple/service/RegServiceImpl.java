package by.innowise.second.simple.service;

import by.innowise.second.simple.entity.Employee;
import by.innowise.second.simple.entity.dto.RegDto;
import by.innowise.second.simple.mapper.EmployeeMapper;
import by.innowise.second.simple.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegServiceImpl implements RegService {

    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Boolean add(RegDto regDto) {
        regDto.getUserDto().setPassword(passwordEncoder.encode(regDto.getUserDto().getPassword()));
        Employee employee = employeeMapper.convertRegDto(regDto);
        employeeRepository.save(employee);
        return true;
    }
}
