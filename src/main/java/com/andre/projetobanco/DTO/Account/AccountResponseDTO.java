package com.andre.projetobanco.DTO.Account;

import com.andre.projetobanco.Domain.Account;

import java.math.BigDecimal;

public record AccountResponseDTO(
        String agency,
        String accountNumber,
        BigDecimal balance,
        String type,
        String ownerName
) {
    public AccountResponseDTO(Account account) {
        this(
                account.getAgency(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getClass().getSimpleName(),
                account.getUser().getFirstName() + " " + account.getUser().getSurname()
        );
    }
}
