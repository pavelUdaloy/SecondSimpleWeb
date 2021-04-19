package by.innowise.second.simple.controller;

import by.innowise.second.simple.entity.dto.TokenDto;
import by.innowise.second.simple.entity.dto.UserDto;
import by.innowise.second.simple.service.AuthService;
import by.innowise.second.simple.controller.security.JwtUtil;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public TokenDto auth(@RequestBody UserDto userDto) {
        return authService.auth(userDto);
    }

    @GetMapping(path = "/refreshtoken")
    public String refreshtoken(HttpServletRequest request) {
        // From the HttpRequest get the claims
        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

//        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
        String subject = claims.getSubject();
        String token = jwtUtil.doGenerateRefreshToken(subject);
        return token;
    }

//    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
//        Map<String, Object> expectedMap = new HashMap<String, Object>();
//        for (Entry<String, Object> entry : claims.entrySet()) {
//            expectedMap.put(entry.getKey(), entry.getValue());
//        }
//        return expectedMap;
//    }
}
