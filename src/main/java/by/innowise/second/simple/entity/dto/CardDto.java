package by.innowise.second.simple.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CardDto {
    private String number;
    private String logicStatus;
    private String cardFirstName;
    private String cardLastName;
    private Long cardAccountId;
}
