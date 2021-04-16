package innowise.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "logic-status")
@PropertySource(value = "classpath:logicstatus.yml", factory = LogicCardStatusSourceFactory.class)
@Getter
@Setter
public class LogicCardStatusProperties {
    private Map<String, String> logicStatuses;
    private String defaultMessage;
}
