package com.andre.projetobanco.Domain;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreditFunctionality {

    public CreditFunctionality() {
    }

    public Boolean processCredit(BigDecimal amount){
        return false;
    }
}
