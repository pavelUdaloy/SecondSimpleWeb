package by.innowise.second.simple.service;

import org.springframework.security.core.Authentication;

public interface UserService {
    Authentication getUser();

    void setUser(String username, String password);
}
