package com.andre.projetobanco.DTO;

import java.util.Objects;

public class AccountCreationDTO {
    private String ownerCpf;
    private String password;
    private String type;

    public AccountCreationDTO(String ownerCpf, String password, String type) {
        this.ownerCpf = ownerCpf;
        this.password = password;
        this.type = type;
    }

    public String getOwnerCpf() {
        return ownerCpf;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AccountCreationDTO that = (AccountCreationDTO) o;
        return Objects.equals(ownerCpf, that.ownerCpf) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerCpf, password);
    }
}
