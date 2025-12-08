package com.andre.projetobanco.DTO;

import java.util.Objects;

public class AccountLoginDTO {
    private String accountNumber;
    private String password;

    public AccountLoginDTO(String accountNumber, String password) {
        this.accountNumber = accountNumber;
        this.password = password;
    }

    public AccountLoginDTO() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AccountLoginDTO that = (AccountLoginDTO) o;
        return Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountNumber);
    }
}
