package innowise.controller;

import innowise.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    @GetMapping(value = "/{id}")
    public Employee get(@PathVariable Integer id){
        Employee employee = new Employee();
        employee.setId(id);
        return employee;
    }
}
