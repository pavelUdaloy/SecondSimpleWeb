package innowise.repository;

import innowise.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    @Query("SELECT r from Role r WHERE r.id = :roleId")
    Role get(Long roleId);
}
