package by.innowise.second.simple.entity.dto;

import by.innowise.second.simple.entity.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String idNumber;
    private Status status;
    private LocalDate birthDate;
}
