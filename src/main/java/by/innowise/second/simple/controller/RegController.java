package by.innowise.second.simple.controller;

import by.innowise.second.simple.entity.dto.RegDto;
import by.innowise.second.simple.service.RegService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/reg")
@AllArgsConstructor
public class RegController {

    //3- jjjjjjjj
    //4- 123123123
    //5- 12345678

    private final RegService regService;

    @PostMapping
    public Boolean post(@RequestBody RegDto regDto) {
        return regService.add(regDto);
    }
}
