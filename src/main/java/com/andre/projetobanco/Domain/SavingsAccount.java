package com.andre.projetobanco.Domain;

import jakarta.persistence.Entity;

@Entity
public class SavingsAccount extends Account {

    public SavingsAccount() {

    }

    public SavingsAccount(Long id, User user, String agency, String accountNumber, String transactionPinHash, Card card) {
        super(id, user, agency, accountNumber, transactionPinHash, card);
    }

    public void creditIncome(){

    }
}
