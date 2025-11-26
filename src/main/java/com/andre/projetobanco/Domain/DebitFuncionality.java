package com.andre.projetobanco.Domain;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DebitFuncionality {

    public DebitFuncionality() {
    }

    public Boolean processDebit(BigDecimal amount, BigDecimal currentBalance){
        return currentBalance.subtract(amount).intValue() >= 0;
    }
}
