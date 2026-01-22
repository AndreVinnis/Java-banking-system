package com.andre.projetobanco.Domain;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class SalaryAccount extends Account {
    public SalaryAccount() {
    }

    public SalaryAccount(Long id, User user, String agency, String accountNumber, BigDecimal balance, String transactionPinHash, Card card) {
        super(id, user, agency, accountNumber, balance, transactionPinHash, card);
    }
}
