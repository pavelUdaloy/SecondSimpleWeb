package by.innowise.second.simple.service;

import by.innowise.second.simple.entity.dto.UserDto;

public interface AuthService {
    //    TokenDto auth(UserDto userDto);
    Boolean auth(UserDto userDto);
}
