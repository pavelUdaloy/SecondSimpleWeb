package by.innowise.second.simple.repository;

import by.innowise.second.simple.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> getCardsByCardAccountId(@Param("id") Long id);
}
