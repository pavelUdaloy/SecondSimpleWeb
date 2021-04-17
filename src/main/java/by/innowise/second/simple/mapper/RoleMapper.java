package by.innowise.second.simple.mapper;

import by.innowise.second.simple.entity.Role;
import by.innowise.second.simple.entity.dto.RoleDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class RoleMapper {
    public Role convertDto(RoleDto roleDto){
        Role role = new Role();
        role.setName(roleDto.getName());
        return role;
    }
}
