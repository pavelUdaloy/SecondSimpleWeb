package by.innowise.second.simple.mapper;

import by.innowise.second.simple.controller.dto.RoleDto;
import by.innowise.second.simple.entity.Role;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class RoleMapper {
    public Role convertDto(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        return role;
    }

    public RoleDto convertDao(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setName(role.getName());
        return roleDto;
    }

    public List<String> convertDaosToString(List<Role> roles) {
        List<String> roleDtos = new ArrayList<>();
        for (Role role : roles) {
            roleDtos.add(role.getName());
        }
        return roleDtos;
    }
}
