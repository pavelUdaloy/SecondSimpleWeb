package innowise.service;

import innowise.entity.Card;
import innowise.entity.CardAccount;
import innowise.entity.dto.CardDto;
import innowise.mapper.CardMapper;
import innowise.properties.LogicCardStatusProperties;
import innowise.repository.CardRepository;
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
    @Transactional
    public CardDto get(Long id) {
        Card card = cardRepository.findById(id).orElse(null);
        if (card != null) {
            return convertLogicStatus(cardMapper.convertDao(card));
        }
        return null;
    }

    @Override
    @Transactional
    public List<CardDto> getAccountCards(Long id) {
        List<Card> cardsByCardAccountId = cardRepository.getCardsByCardAccountId(id);
        return convertLogicStatuses(cardMapper.convertDaos(cardsByCardAccountId));
    }

    @Override
    @Transactional
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
        if (logicCardStatusProperties.getLogicStatuses().containsKey(logicStatus)) {
            cardDto.setLogicStatus
                    (logicCardStatusProperties.getLogicStatuses().get(logicStatus));
        } else {
            cardDto.setLogicStatus
                    (logicCardStatusProperties.getDefaultMessage());
        }
    }
}
