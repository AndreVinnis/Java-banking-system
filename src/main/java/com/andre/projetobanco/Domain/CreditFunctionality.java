package com.andre.projetobanco.Domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class CreditFunctionality {
    private BigDecimal creditLimit;
    private BigDecimal currentBalance;
    private final Set<Invoice> invoices = new HashSet<>();

    public CreditFunctionality(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
        currentBalance = BigDecimal.ZERO;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public Boolean processCredit(BigDecimal amount){
        if(creditLimit.subtract(amount).intValue() >= 0 && creditLimit.subtract(currentBalance).compareTo(amount) >= 0){
            currentBalance = currentBalance.add(amount);
            return true;
        }
        return false;
    }
}
