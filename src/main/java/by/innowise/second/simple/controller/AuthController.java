package by.innowise.second.simple.controller;

import by.innowise.second.simple.controller.dto.TokenDto;
import by.innowise.second.simple.controller.dto.UserDto;
import by.innowise.second.simple.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
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
    public TokenDto auth(@RequestBody UserDto userDto) {
        return authService.auth(userDto);
    }

    @GetMapping(path = "/refresh")
    public TokenDto refresh() {
        return authService.refresh(SecurityContextHolder.getContext().getAuthentication());
    }
}
