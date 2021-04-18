package by.innowise.second.simple.service;

import by.innowise.second.simple.entity.Employee;
import by.innowise.second.simple.entity.dto.UserDto;
import by.innowise.second.simple.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

//    private AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;

    @Override
//    public TokenDto auth(UserDto userDto) {
    public Boolean auth(UserDto userDto) {
        Employee employee = employeeRepository.findByUsername(userDto.getUsername());
        return passwordEncoder.matches(userDto.getPassword(), employee.getPassword());
//        return null;
//        authenticationManager.authenticate()
    }
}
