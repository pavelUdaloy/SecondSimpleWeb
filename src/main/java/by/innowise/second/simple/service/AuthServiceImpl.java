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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

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
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            String accessToken = jwtUtil.generateAccessToken(userDto.getUsername());
            String refreshToken = jwtUtil.generateRefreshToken(userDto.getUsername());
            TokenDto tokenDto = new TokenDto();
            tokenDto.setAccess(accessToken);
            tokenDto.setRefresh(refreshToken);
            return tokenDto;
        } else {
            throw new UsernameNotFoundException("Wrong password");
        }
    }

    @Override
    public String refresh() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return jwtUtil.generateAccessToken(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username);
        UserDto userDto = employeeMapper.convertToUserDto(employee);
        return new User(userDto.getUsername(), userDto.getPassword(), true,
                true, true, true, new HashSet<>());
    }
}
