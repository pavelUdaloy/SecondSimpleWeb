package by.innowise.second.simple.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TokenDto {
    private String access;
    private String refresh;
}
