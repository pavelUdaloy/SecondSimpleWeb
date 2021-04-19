package by.innowise.second.simple.service;

import by.innowise.second.simple.entity.dto.TokenDto;
import by.innowise.second.simple.entity.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    TokenDto auth(UserDto userDto);
}
