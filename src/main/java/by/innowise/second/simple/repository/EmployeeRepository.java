package by.innowise.second.simple.repository;

import by.innowise.second.simple.entity.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @EntityGraph(value = "Employee.cardAccounts")
    Optional<Employee> findById(@Param("id") Long id);

    @EntityGraph(value = "Employee.cardAccounts")
    List<Employee> getAllBy();

    @EntityGraph(value = "Employee.roles")
    List<Employee> findAllBy();
}
