package com.andre.projetobanco.Domain;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class SavingsAccount extends Account {

    public SavingsAccount() {

    }

    public SavingsAccount(Long id, User user, String agency, String accountNumber, BigDecimal balance, String transactionPinHash, Card card) {
        super(id, user, agency, accountNumber, balance, transactionPinHash, card);
    }

    public void creditIncome(){

    }
}
