package innowise.controller;

import innowise.entity.Employee;
import innowise.entity.dto.EmployeeDto;
import innowise.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/{id}")
    public Employee get(@PathVariable Long id){
        Employee employee = employeeService.get(id);
        return employee;
    }

    @GetMapping
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

    @PostMapping
    public Employee post(@RequestBody EmployeeDto employeeDto){
        return employeeService.add(employeeDto);
    }
}
