package by.innowise.second.simple.controller;

import by.innowise.second.simple.entity.dto.EmployeeDto;
import by.innowise.second.simple.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(value = "/{id}")
    public EmployeeDto get(@PathVariable Long id){
        return employeeService.get(id);
    }

    @GetMapping
    public List<EmployeeDto> getAll(){
        return employeeService.getAllWithRoles();
    }

    @PostMapping
    public EmployeeDto post(@RequestBody EmployeeDto employeeDto){
        return employeeService.add(employeeDto);
    }
}
