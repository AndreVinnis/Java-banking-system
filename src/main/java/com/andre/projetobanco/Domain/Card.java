package com.andre.projetobanco.Domain;

import com.andre.projetobanco.Enums.Flag;

import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

public class Card {
    private final Long id;
    private final String cardNumber;
    private final String cvv;
    private final Flag flag;
    private YearMonth validity;
    private String pinCardHash;

    private final DebitFuncionality debitFuncionality;
    private CreditFunctionality creditFunctionality;
    private final Set<Card> cardTransactions = new HashSet<>();

    public Card(Long id, String cardNumber, String cvv, Flag flag, YearMonth validity, String pinCardHash, DebitFuncionality debitFuncionality, CreditFunctionality creditFunctionality) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.flag = flag;
        this.validity = validity;
        this.pinCardHash = pinCardHash;
        this.debitFuncionality = debitFuncionality;
        this.creditFunctionality = creditFunctionality;
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public Flag getFlag() {
        return flag;
    }

    public YearMonth getValidity() {
        return validity;
    }

    public void setValidity(YearMonth validity) {
        this.validity = validity;
    }

    public String getPinCardHash() {
        return pinCardHash;
    }

    public void setPinCardHash(String pinCardHash) {
        this.pinCardHash = pinCardHash;
    }

    public DebitFuncionality getDebitFuncionality() {
        return debitFuncionality;
    }

    public CreditFunctionality getCreditFunctionality() {
        return creditFunctionality;
    }

    public void setCreditFunctionality(CreditFunctionality creditFunctionality) {
        this.creditFunctionality = creditFunctionality;
    }

    public Set<Card> getCardTransactions() {
        return cardTransactions;
    }
}
