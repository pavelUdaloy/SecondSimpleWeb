package innowise.service;

import innowise.entity.dto.CardDto;

import java.util.List;

public interface CardService {
    CardDto get(Long id);

    List<CardDto> getAccountCards(Long id);

    List<CardDto> getAll();

    CardDto add(CardDto cardDto);
}
