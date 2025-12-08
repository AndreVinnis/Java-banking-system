package com.andre.projetobanco.DTO;

import java.util.Objects;

public class UserLoginDTO {
    private String cpf;
    private String password;

    public UserLoginDTO(String cpf, String password) {
        this.cpf = cpf;
        this.password = password;
    }

    public UserLoginDTO() {
    }

    public String getCpf() {
        return cpf;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginDTO that = (UserLoginDTO) o;
        return Objects.equals(cpf, that.cpf) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cpf);
    }
}
