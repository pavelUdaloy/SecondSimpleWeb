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

    //todo корректно ли размещать здесь конвертацию logic statuses?

    private final LogicCardStatusProperties logicCardStatusProperties;
    private final CardService cardService;

    @GetMapping(value = "/{id}")
    public CardDto get(@PathVariable Long id) {
        return convertLogicStatus(cardService.get(id));
    }

    @GetMapping
    public List<CardDto> getAll() {
        return convertLogicStatuses(cardService.getAll());
    }

    @GetMapping("/acc/{id}")
    public List<CardDto> getAllByAcc(@PathVariable Long id) {
        return convertLogicStatuses(cardService.getAccountCards(id));
    }

    @PostMapping
    public CardDto post(@RequestBody CardDto cardDto) {
        return convertLogicStatus(cardService.add(cardDto));
    }

    private CardDto convertLogicStatus(CardDto cardDto) {
        convertStatus(cardDto);
        return cardDto;
    }

    private List<CardDto> convertLogicStatuses(List<CardDto> cardDtos) {
        for (CardDto cardDto : cardDtos) {
            convertStatus(cardDto);
        }
        return cardDtos;
    }

    private void convertStatus(CardDto cardDto) {
        String logicStatus = cardDto.getLogicStatus();
        if (logicCardStatusProperties.getLogicStatuses().containsKey(logicStatus)) {
            cardDto.setLogicStatus
                    (logicCardStatusProperties.getLogicStatuses().get(logicStatus));
        } else {
            cardDto.setLogicStatus
                    (logicCardStatusProperties.getDefaultMessage());
        }
    }
}
