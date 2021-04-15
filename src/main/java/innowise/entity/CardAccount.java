package innowise.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "card_accounts")
@ToString
public class CardAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_account_generator")
    @SequenceGenerator(name = "card_account_generator", sequenceName = "card_accounts_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "score_number")
    private String scoreNumber;
    private String currency;
    @Enumerated(EnumType.STRING)
    private Status status;
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<Card> cards;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Employee employee;
}
