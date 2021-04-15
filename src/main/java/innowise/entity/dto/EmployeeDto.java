package innowise.entity.dto;

import innowise.entity.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
