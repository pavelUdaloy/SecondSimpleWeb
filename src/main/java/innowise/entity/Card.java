//package innowise.entity;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table(name = "cards")
//public class Card {
//    @Id
//    private Long id;
//    private String number;
//    @Column(name = "logic_status")
//    private String logicStatus;
//    @Column(name = "card_first_name")
//    private String cardFirstName;
//    @Column(name = "card_last_name")
//    private String cardLastName;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "owner_id")
//    private CardAccount cardAccount;
//}
