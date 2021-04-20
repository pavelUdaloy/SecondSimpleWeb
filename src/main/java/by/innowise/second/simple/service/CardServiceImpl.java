package by.innowise.second.simple.service;

import by.innowise.second.simple.entity.Card;
import by.innowise.second.simple.entity.CardAccount;
import by.innowise.second.simple.controller.dto.CardDto;
import by.innowise.second.simple.mapper.CardMapper;
import by.innowise.second.simple.properties.LogicCardStatusProperties;
import by.innowise.second.simple.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final LogicCardStatusProperties logicCardStatusProperties;

    @Override
    public CardDto get(Long id) {
        Card card = cardRepository.findById(id).orElse(null);
        if (card != null) {
            return convertLogicStatus(cardMapper.convertDao(card));
        }
        return null;
    }

    @Override
    public List<CardDto> getAccountCards(Long id) {
        List<Card> cardsByCardAccountId = cardRepository.getCardsByCardAccountId(id);
        return convertLogicStatuses(cardMapper.convertDaos(cardsByCardAccountId));
    }

    @Override
    public List<CardDto> getAll() {
        List<Card> cards = cardRepository.findAll();
        return convertLogicStatuses(cardMapper.convertDaos(cards));
    }

    @Override
    @Transactional
    public CardDto add(CardDto cardDto) {
        CardAccount cardAccount = new CardAccount();
        cardAccount.setId(cardDto.getCardAccountId());
        Card card = cardMapper.convertDto(cardDto, cardAccount);
        Card savedCard = cardRepository.save(card);
        return convertLogicStatus(cardMapper.convertDao(savedCard));
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
        String defaultMessage = logicCardStatusProperties.getDefaultMessage();
        String orDefault = logicCardStatusProperties.getLogicStatuses().getOrDefault(logicStatus, defaultMessage);
        cardDto.setLogicStatus(orDefault);
    }
}
