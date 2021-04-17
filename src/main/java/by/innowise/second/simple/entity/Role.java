package by.innowise.second.simple.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name = "role_generator", sequenceName = "roles_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @ManyToMany(targetEntity = Employee.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roles")
    private List<Employee> employees;
}
