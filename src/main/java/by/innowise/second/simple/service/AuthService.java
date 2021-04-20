package by.innowise.second.simple.service;

import by.innowise.second.simple.controller.dto.TokenDto;
import by.innowise.second.simple.controller.dto.UserDto;
import org.springframework.security.core.Authentication;

public interface AuthService {
    TokenDto auth(UserDto userDto);

    TokenDto refresh(Authentication authentication);
}
