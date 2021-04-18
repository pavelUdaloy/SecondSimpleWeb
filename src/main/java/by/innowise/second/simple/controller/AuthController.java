package by.innowise.second.simple.controller;

import by.innowise.second.simple.entity.dto.UserDto;
import by.innowise.second.simple.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
//    public TokenDto post(@RequestBody UserDto userDto) {
    public Boolean post(@RequestBody UserDto userDto) {
        return authService.auth(userDto);
    }
}