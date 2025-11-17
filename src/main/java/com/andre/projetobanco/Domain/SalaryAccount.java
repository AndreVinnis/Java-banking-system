package com.andre.projetobanco.Domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SalaryAccount extends Account {

    public SalaryAccount(Long id, User user, String agency, String accountNumber, BigDecimal balance, String transactionPinHash, LocalDateTime createdAt, Card card) {
        super(id, user, agency, accountNumber, balance, transactionPinHash, createdAt, card);
    }
}
