package by.innowise.second.simple.service;

import by.innowise.second.simple.controller.dto.TokenDto;
import by.innowise.second.simple.controller.dto.UserDto;
import by.innowise.second.simple.entity.Employee;
import by.innowise.second.simple.repository.EmployeeRepository;
import by.innowise.second.simple.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final JwtUtil jwtUtil;

    @Override
    public TokenDto auth(UserDto userDto) {
        Employee employee = employeeRepository.findByUsername(userDto.getUsername());
        if (!passwordEncoder.matches(userDto.getPassword(), employee.getPassword())) {
            throw new UsernameNotFoundException("Wrong password");
        }
        String accessToken = jwtUtil.generateAccessToken(userDto.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(userDto.getUsername());
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccess(accessToken);
        tokenDto.setRefresh(refreshToken);
        return tokenDto;
    }

    @Override
    public TokenDto refresh(Authentication authentication) {
        String username = authentication.getName();
        String accessToken = jwtUtil.generateAccessToken(username);
        String refreshToken = jwtUtil.generateRefreshToken(username);
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccess(accessToken);
        tokenDto.setRefresh(refreshToken);
        return tokenDto;
    }
}
