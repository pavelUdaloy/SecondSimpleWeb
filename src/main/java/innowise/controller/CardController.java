package innowise.controller;

import innowise.entity.Card;
import innowise.entity.dto.CardDto;
import innowise.properties.LogicCardStatusProperties;
import innowise.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/card")
@AllArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping(value = "/{id}")
    public CardDto get(@PathVariable Long id) {
        return cardService.get(id);
    }

    @GetMapping
    public List<CardDto> getAll() {
        return cardService.getAll();
    }

    @GetMapping("/acc/{id}")
    public List<CardDto> getAllByAcc(@PathVariable Long id) {
        return cardService.getAccountCards(id);
    }

    @PostMapping
    public CardDto post(@RequestBody CardDto cardDto) {
        return cardService.add(cardDto);
    }
}
