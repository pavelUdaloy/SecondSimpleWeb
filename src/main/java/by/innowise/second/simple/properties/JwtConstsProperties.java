package by.innowise.second.simple.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt-consts")
public class JwtConstsProperties {
    @NotBlank
    private String secret;
    @NotNull
    private Integer accessExpirationDateInMs;
    @NotNull
    private Integer refreshExpirationDateInMs;
}
