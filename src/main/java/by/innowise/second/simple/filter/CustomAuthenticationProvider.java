package by.innowise.second.simple.filter;

import by.innowise.second.simple.entity.Employee;
import by.innowise.second.simple.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@AllArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final EmployeeRepository employeeRepository;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        Employee employee = employeeRepository.findByUsername(username);
        User userDetails = new User(employee.getUsername(), employee.getPassword(), true,
                true, true, true, new HashSet<>());
        return new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
