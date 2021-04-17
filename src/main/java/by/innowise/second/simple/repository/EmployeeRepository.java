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
    @EntityGraph(attributePaths = "cardAccounts")
    Optional<Employee> findById(@Param("id") Long id);
//todo лучше юзать NamedEntityGraph
    @EntityGraph(attributePaths = "cardAccounts")
    List<Employee> getAllBy();

    @EntityGraph(attributePaths = "roles")
    List<Employee> findAllBy();
}
