package by.innowise.second.simple.service;

import by.innowise.second.simple.entity.dto.AuthDto;
import by.innowise.second.simple.entity.dto.TokenDto;
import lombok.AllArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

//    private AuthenticationManager authenticationManager;

    @Override
    public TokenDto auth(AuthDto authDto) {
//        authenticationManager.authenticate()
        return null;
    }
}
