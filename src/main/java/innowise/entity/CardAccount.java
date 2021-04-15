package innowise.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

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
@DynamicUpdate
@Table(name = "card_accounts")
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
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "cardAccount")
    private List<Card> cards;
    @JoinColumn(name = "owner_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;
}
