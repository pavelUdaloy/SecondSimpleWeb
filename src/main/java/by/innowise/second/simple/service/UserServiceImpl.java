package by.innowise.second.simple.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Authentication getUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public void setUser(String username, String password) {
        UserDetails userDetails = new User(username, password, new HashSet<>());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()));
    }
}
