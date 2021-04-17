package by.innowise.second.simple.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "logic-status")
public class LogicCardStatusProperties {
    @NotBlank
    private Map<String, String> logicStatuses;
    @NotBlank
    private String defaultMessage;
}