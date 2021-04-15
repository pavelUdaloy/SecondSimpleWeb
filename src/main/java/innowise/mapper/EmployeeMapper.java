package innowise.mapper;

import innowise.entity.Employee;
import innowise.entity.dto.EmployeeDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class EmployeeMapper {
    public Employee convertDto(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setPatronymic(employeeDto.getPatronymic());
        employee.setIdNumber(employeeDto.getIdNumber());
        employee.setStatus(employeeDto.getStatus());
        employee.setBirthDate(employeeDto.getBirthDate());
        return employee;
    }
}
