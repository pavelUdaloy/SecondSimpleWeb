package innowise.repository;

import innowise.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query("SELECT e from Employee e left join fetch e.cardAccounts WHERE e.id = :empId")
    Employee get(Long empId);

    @Query(value = "select e from Employee e left join fetch e.cardAccounts")
    List<Employee> getAll();
}
