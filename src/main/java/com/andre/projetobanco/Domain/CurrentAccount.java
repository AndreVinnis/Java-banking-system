package com.andre.projetobanco.Domain;

import jakarta.persistence.Entity;
import org.springframework.security.core.GrantedAuthority;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
public class CurrentAccount extends Account {
    private BigDecimal overdrawLimit;
    private BigDecimal maintenanceFee;

    public CurrentAccount() {

    }

    public CurrentAccount(Long id, User user, String agency, String accountNumber, BigDecimal balance, String transactionPinHash, Card card, BigDecimal overdrawLimit, BigDecimal maintenanceFee) {
        super(id, user, agency, accountNumber, balance, transactionPinHash, card);
        this.overdrawLimit = overdrawLimit;
        this.maintenanceFee = maintenanceFee;
    }

    public BigDecimal getOverdrawLimit() {
        return overdrawLimit;
    }

    public void setOverdrawLimit(BigDecimal overdrawLimit) {
        this.overdrawLimit = overdrawLimit;
    }

    public BigDecimal getMaintenanceFee() {
        return maintenanceFee;
    }

    public void setMaintenanceFee(BigDecimal maintenanceFee) {
        this.maintenanceFee = maintenanceFee;
    }
}
