package innowise.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Role {
    private Integer id;
    private String name;
    private LocalDateTime creationDate;
    //mtm
    private List<Employee> employees;
}
