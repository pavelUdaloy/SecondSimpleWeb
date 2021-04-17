package by.innowise.second.simple.service;

import by.innowise.second.simple.entity.dto.AuthDto;
import by.innowise.second.simple.entity.dto.TokenDto;

public interface AuthService {
    TokenDto auth(AuthDto authDto);
}
