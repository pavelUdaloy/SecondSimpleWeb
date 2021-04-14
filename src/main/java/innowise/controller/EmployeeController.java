package innowise.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auf")
public class EmployeeController {
    @GetMapping
    public String main2(){
        return "boot xyi";
    }
}
