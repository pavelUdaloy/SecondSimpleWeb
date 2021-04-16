package innowise.entity.dto;

import innowise.entity.CardAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
