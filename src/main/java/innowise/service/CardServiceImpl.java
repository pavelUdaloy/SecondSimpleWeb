package innowise.service;

import innowise.entity.Card;
import innowise.entity.CardAccount;
import innowise.entity.dto.CardDto;
import innowise.mapper.CardMapper;
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

    @Override
    @Transactional
    public CardDto get(Long id) {
        Card card = cardRepository.findById(id).orElse(null);
        if (card != null) {
            return cardMapper.convertDao(card);
        }
        return null;
    }

    @Override
    @Transactional
    public List<CardDto> getAccountCards(Long id) {
        List<Card> cardsByCardAccountId = cardRepository.getCardsByCardAccountId(id);
        return cardMapper.convertDaos(cardsByCardAccountId);
    }

    @Override
    @Transactional
    public List<CardDto> getAll() {
        List<Card> cards = cardRepository.findAll();
        return cardMapper.convertDaos(cards);
    }

    @Override
    @Transactional
    public CardDto add(CardDto cardDto) {
        CardAccount cardAccount = new CardAccount();
        cardAccount.setId(cardDto.getCardAccountId());
        Card card = cardMapper.convertDto(cardDto, cardAccount);
        Card savedCard = cardRepository.save(card);
        return cardMapper.convertDao(savedCard);
    }
}
