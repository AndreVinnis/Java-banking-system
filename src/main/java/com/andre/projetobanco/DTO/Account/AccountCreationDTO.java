package com.andre.projetobanco.DTO.Account;

import com.andre.projetobanco.Enums.AccountType;

public class AccountCreationDTO {

    private AccountType accountType;
    private String ownerCpf;
    private String transactionPin;

    public AccountCreationDTO(AccountType accountType, String ownerCpf, String transactionPin) {
        this.accountType = accountType;
        this.ownerCpf = ownerCpf;
        this.transactionPin = transactionPin;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getOwnerCpf() {
        return ownerCpf;
    }

    public void setOwnerCpf(String ownerCpf) {
        this.ownerCpf = ownerCpf;
    }

    public String getTransactionPin() {
        return transactionPin;
    }

    public void setTransactionPin(String transactionPinHash) {
        this.transactionPin = transactionPinHash;
    }
}
