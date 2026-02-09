package com.andre.projetobanco.Domain;

public class SalaryAccount extends Account {

    public SalaryAccount(Long id, User user, String agency, String accountNumber, String transactionPinHash, Card card) {
        super(id, user, agency, accountNumber, transactionPinHash, card);
    }

    public SalaryAccount() {
    }
}
