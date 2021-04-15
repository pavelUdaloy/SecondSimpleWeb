package innowise.controller;

import innowise.entity.Employee;
import innowise.entity.dto.EmployeeDto;
import innowise.service.EmployeeService;
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
        return employeeService.getAll();
    }

    @PostMapping
    public EmployeeDto post(@RequestBody EmployeeDto employeeDto){
        return employeeService.add(employeeDto);
    }
}
