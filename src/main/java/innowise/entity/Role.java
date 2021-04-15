//package innowise.entity;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table(name = "roles")
//public class Role {
//    @Id
//    private Long id;
//    private String name;
//    @Column(name = "creation_date")
//    private LocalDateTime creationDate;
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Employee> employees;
//}
