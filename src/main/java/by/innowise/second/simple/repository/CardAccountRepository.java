package by.innowise.second.simple.repository;

import by.innowise.second.simple.entity.CardAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardAccountRepository extends JpaRepository<CardAccount, Long> {
}
