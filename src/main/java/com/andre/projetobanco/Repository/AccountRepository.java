package com.andre.projetobanco.Repository;

import com.andre.projetobanco.Domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByAccountNumber(String AccountNumber);
}
