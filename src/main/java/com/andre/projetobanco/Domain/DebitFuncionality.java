package com.andre.projetobanco.Domain;

import java.math.BigDecimal;

public class DebitFuncionality {

    public DebitFuncionality() {
    }

    public Boolean processDebit(BigDecimal amount, BigDecimal currentBalance){
        return currentBalance.subtract(amount).intValue() >= 0;
    }
}
