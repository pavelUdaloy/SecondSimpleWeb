package by.innowise.second.simple.service;

import by.innowise.second.simple.controller.dto.TokenDto;
import by.innowise.second.simple.controller.dto.UserDto;
import by.innowise.second.simple.entity.Employee;
import by.innowise.second.simple.mapper.RoleMapper;
import by.innowise.second.simple.repository.EmployeeRepository;
import by.innowise.second.simple.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final RoleMapper roleMapper;
    private final JwtUtil jwtUtil;

    @Override
    public TokenDto auth(UserDto userDto) {
        Employee employee = employeeRepository.findByUsername(userDto.getUsername());
        if (!passwordEncoder.matches(userDto.getPassword(), employee.getPassword())) {
            throw new UsernameNotFoundException("Wrong password");
        }
        List<String> roles = roleMapper.convertDaosToString(employee.getRoles());

        String accessToken = jwtUtil.generateAccessToken(userDto.getUsername(), roles);
        String refreshToken = jwtUtil.generateRefreshToken(userDto.getUsername());
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccess(accessToken);
        tokenDto.setRefresh(refreshToken);
        return tokenDto;
    }

    @Override
    public TokenDto refresh(Authentication authentication) {
        String username = authentication.getName();
        Employee employee = employeeRepository.findByUsername(username);
        List<String> roles = roleMapper.convertDaosToString(employee.getRoles());
        String accessToken = jwtUtil.generateAccessToken(username, roles);
        String refreshToken = jwtUtil.generateRefreshToken(username);
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccess(accessToken);
        tokenDto.setRefresh(refreshToken);
        return tokenDto;
    }
}
