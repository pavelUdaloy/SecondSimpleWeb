package by.innowise.second.simple;

import by.innowise.second.simple.properties.JwtConstsProperties;
import by.innowise.second.simple.properties.LogicCardStatusProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties({LogicCardStatusProperties.class, JwtConstsProperties.class})
public class SecondSimpleWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecondSimpleWebApplication.class, args);
    }
}
