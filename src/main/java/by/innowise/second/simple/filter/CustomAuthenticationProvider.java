package by.innowise.second.simple.filter;

import by.innowise.second.simple.entity.Employee;
import by.innowise.second.simple.mapper.RoleMapper;
import by.innowise.second.simple.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final EmployeeRepository employeeRepository;
    private final RoleMapper roleMapper;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        Employee employee = employeeRepository.findByUsername(username);
        List<String> rolesBody = roleMapper.convertDaosToString(employee.getRoles());
        List<SimpleGrantedAuthority> roles = rolesBody.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        User userDetails = new User(employee.getUsername(), employee.getPassword(), true,
                true, true, true, roles);
        return new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
