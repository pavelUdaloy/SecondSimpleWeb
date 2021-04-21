package by.innowise.second.simple.mapper;

import by.innowise.second.simple.controller.dto.EmployeeDto;
import by.innowise.second.simple.controller.dto.RegDto;
import by.innowise.second.simple.controller.dto.UserDto;
import by.innowise.second.simple.entity.Employee;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class EmployeeMapper {
    public Employee convertDto(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setPatronymic(employeeDto.getPatronymic());
        employee.setIdNumber(employeeDto.getIdNumber());
        employee.setStatus(employeeDto.getStatus());
        employee.setBirthDate(employeeDto.getBirthDate());
        return employee;
    }

    public UserDto convertToUserDto(Employee employee) {
        UserDto userDto = new UserDto();
        userDto.setPassword(employee.getPassword());
        userDto.setUsername(employee.getUsername());
        return userDto;
    }

    public Employee convertRegDto(RegDto regDto) {
        Employee employee = convertDto(regDto.getEmployeeDto());
        employee.setPassword(regDto.getUserDto().getPassword());
        employee.setUsername(regDto.getUserDto().getUsername());
        return employee;
    }

    public EmployeeDto convertDao(Employee employee) {
        return convertDaoToDto(employee);
    }

    public List<EmployeeDto> convertDaos(List<Employee> employees) {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDto employeeDto = convertDaoToDto(employee);
            employeeDtos.add(employeeDto);
        }
        return employeeDtos;
    }

    private EmployeeDto convertDaoToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setPatronymic(employee.getPatronymic());
        employeeDto.setIdNumber(employee.getIdNumber());
        employeeDto.setStatus(employee.getStatus());
        employeeDto.setBirthDate(employee.getBirthDate());
        return employeeDto;
    }
}
