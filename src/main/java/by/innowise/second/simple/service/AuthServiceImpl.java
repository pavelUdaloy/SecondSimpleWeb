package by.innowise.second.simple.service;

import by.innowise.second.simple.controller.security.JwtUtil;
import by.innowise.second.simple.entity.Employee;
import by.innowise.second.simple.entity.dto.TokenDto;
import by.innowise.second.simple.entity.dto.UserDto;
import by.innowise.second.simple.mapper.EmployeeMapper;
import by.innowise.second.simple.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public TokenDto auth(UserDto userDto) {
        Employee employee = employeeRepository.findByUsername(userDto.getUsername());
        boolean matches = passwordEncoder.matches(userDto.getPassword(), employee.getPassword());

        if (matches) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userDto.getUsername(), userDto.getPassword()));//todo тут хз

            UserDetails userdetails = loadUserByUsername(userDto.getUsername());
            String accessToken = jwtUtil.generateToken(userdetails);
            String refreshToken = jwtUtil.doGenerateRefreshToken(userdetails.getUsername());
            TokenDto tokenDto = new TokenDto();
            tokenDto.setAccess(accessToken);
            tokenDto.setRefresh(refreshToken);
            return tokenDto;
        } else {
            throw new UsernameNotFoundException("Wrong password");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = getByUsername(username);
        if (Objects.isNull(userDto)) {
            throw new UsernameNotFoundException(String.format("Employee %s is not found", username));
        }
        HashSet<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(userDto.getUsername(), userDto.getPassword(), true,
                true, true, true, authorities);
    }

    public UserDto getByUsername(String username) {
        Employee employee = employeeRepository.findByUsername(username);
        return employeeMapper.convertToUserDto(employee);
    }
}
