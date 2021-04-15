package innowise.entity.dto;

import innowise.entity.Status;
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
