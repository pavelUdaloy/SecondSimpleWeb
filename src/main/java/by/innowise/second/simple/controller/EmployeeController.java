package by.innowise.second.simple.controller;

import by.innowise.second.simple.controller.dto.EmployeeDto;
import by.innowise.second.simple.controller.dto.FilterDto;
import by.innowise.second.simple.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(value = "/{id}")
    public EmployeeDto get(@PathVariable Long id) {
        return employeeService.get(id);
    }

    @GetMapping
    public List<EmployeeDto> getAllWithFilters(@Valid FilterDto filterDto,
                                               Pageable pageable,
                                               HttpServletRequest request) {
        if (request.getParameterMap().size() > 7) {
            throw new RequestRejectedException("Wrong number of params in req");
        }
        return employeeService.getAllWithPag(pageable, filterDto);
    }
}
