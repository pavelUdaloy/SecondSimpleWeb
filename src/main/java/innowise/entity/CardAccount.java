package innowise.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CardAccount {
    private Integer id;
    private String scoreNumber;
    private String currency;
    private Status status;
}
