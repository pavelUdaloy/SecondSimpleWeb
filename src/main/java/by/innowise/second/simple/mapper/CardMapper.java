package by.innowise.second.simple.mapper;

import by.innowise.second.simple.entity.Card;
import by.innowise.second.simple.entity.CardAccount;
import by.innowise.second.simple.entity.dto.CardDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class CardMapper {
    public CardDto convertDao(Card card) {
        return convertDaoToDto(card);
    }

    private CardDto convertDaoToDto(Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setCardAccountId(card.getCardAccount().getId());
        cardDto.setCardFirstName(card.getCardFirstName());
        cardDto.setCardLastName(card.getCardLastName());
        cardDto.setNumber(card.getNumber());
        cardDto.setLogicStatus(card.getLogicStatus());
        return cardDto;
    }

    public List<CardDto> convertDaos(List<Card> cards) {
        List<CardDto> cardDtos = new ArrayList<>();
        for (Card card : cards) {
            CardDto cardDto = convertDaoToDto(card);
            cardDtos.add(cardDto);
        }
        return cardDtos;
    }

    public Card convertDto(CardDto cardDto, CardAccount cardAccount) {
        Card card = new Card();
        card.setCardFirstName(cardDto.getCardFirstName());
        card.setCardLastName(cardDto.getCardLastName());
        card.setNumber(cardDto.getNumber());
        card.setLogicStatus(cardDto.getLogicStatus());
        card.setCardAccount(cardAccount);
        return card;
    }
}
