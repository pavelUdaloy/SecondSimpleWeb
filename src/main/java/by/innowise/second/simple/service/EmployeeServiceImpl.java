package by.innowise.second.simple.service;

import by.innowise.second.simple.controller.dto.EmployeeDto;
import by.innowise.second.simple.controller.dto.FilterDto;
import by.innowise.second.simple.entity.Employee;
import by.innowise.second.simple.entity.Status;
import by.innowise.second.simple.mapper.EmployeeMapper;
import by.innowise.second.simple.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.EmptyStackException;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto get(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            return employeeMapper.convertDao(employee);
        } else {
            throw new EmptyStackException();
        }
    }

    @Override
    public List<EmployeeDto> getAllWithPag(Pageable pageable, FilterDto filterDto) {
        List<Employee> employees = employeeRepository.findAll
                (createSpecification(filterDto), pageable).toList();
        return employeeMapper.convertDaos(employees);
    }

    private Specification<Employee> createSpecification(FilterDto filterDto) {
        return Specification
                .where(getSpecForBDLess(filterDto.getBirthDateLess()))
                .and(getSpecForBDMore(filterDto.getBirthDateMore()))
                .and(getSpecForIdNumber(filterDto.getIdNumber()))
                .and(getSpecForStatuses(filterDto.getStatuses()));
    }

    private Specification<Employee> getSpecForBDLess(LocalDate birthDateLess) {
        if (birthDateLess == null) {
            return null;
        } else {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo
                            (root.get("birthDate"), birthDateLess);
        }
    }

    private Specification<Employee> getSpecForBDMore(LocalDate birthDateLess) {
        if (birthDateLess == null) {
            return null;
        } else {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo
                            (root.get("birthDate"), birthDateLess);
        }
    }

    private Specification<Employee> getSpecForIdNumber(String idNumber) {
        if (idNumber == null) {
            return null;
        } else {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.like
                            (root.get("idNumber"), "%" + idNumber + "%");
        }
    }

    private Specification<Employee> getSpecForStatuses(List<Status> statuses) {
        if (statuses == null) {
            return null;
        } else {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.in
                            (root.get("status")).value(statuses);
        }
    }
}