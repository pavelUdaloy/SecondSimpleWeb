package by.innowise.second.simple.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt-consts")
public class JwtConstsProperties {
    @NotBlank
    private String secret;
    @NotBlank
    private String signatureAlgorithm;
    @NotNull
    private Duration accessExpirationDateInMs;
    @NotNull
    private Duration refreshExpirationDateInMs;
}
