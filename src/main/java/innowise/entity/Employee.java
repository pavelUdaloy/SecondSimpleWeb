package innowise.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate idNumber;
    private Status status;
    //otm
    private List<CardAccount> cardAccounts;
    //mtm
    private List<Role> roles;
}
