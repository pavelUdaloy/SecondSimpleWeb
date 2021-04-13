package innowise.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Card {
    private Integer id;
    private String number;
    private String logicStatus;
    private String cardFirstName;
    private String cardLastName;
}
