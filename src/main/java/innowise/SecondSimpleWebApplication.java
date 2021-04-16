package innowise;

import innowise.properties.LogicCardStatusProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SecondSimpleWebApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SecondSimpleWebApplication.class, args);
    }

    @Autowired
    private LogicCardStatusProperties logicCardStatusProperties;

    @Override
    public void run(String... args) throws Exception {
        for (Map.Entry<String, String> stringStringEntry : logicCardStatusProperties.getLogicStatuses().entrySet()) {
            System.out.println(stringStringEntry);
        }
    }
}
