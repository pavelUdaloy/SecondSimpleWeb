package by.innowise.second.simple.controller.dto;

import by.innowise.second.simple.entity.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FilterDto {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDateLess;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDateMore;
    @Size(min = 1, max = 12)
    private String idNumber;
    private List<Status> statuses;
}
