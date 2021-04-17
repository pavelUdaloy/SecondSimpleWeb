package by.innowise.second.simple.entity.dto;

import by.innowise.second.simple.entity.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CardAccountDto {
    private Long id;
    private String scoreNumber;
    private String currency;
    private Status status;
}
