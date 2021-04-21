package by.innowise.second.simple.service;

import by.innowise.second.simple.controller.dto.CardDto;

import java.util.List;

public interface CardService {
    CardDto get(Long id);

    List<CardDto> getAccountCards(Long id);

    List<CardDto> getAll();

    CardDto add(CardDto cardDto);
}
