package com.andre.projetobanco.Domain;


import com.andre.projetobanco.Enums.CardType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Objects;

@Entity
@Table(name = "tb_card_transactions")
public class CardTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String merchantName;
    private Integer installmentCount;
    private Integer installmentsPaid;
    private YearMonth invoiceDateOfNextInstallment;
    private String authorizationCode;
    private CardType cardType;
    private LocalDateTime transactionDate;

    public CardTransaction() {
    }

    public CardTransaction(Long id, String merchantName, Integer installmentCount, YearMonth invoiceDateOfNextInstallment, String authorizationCode, CardType cardType, LocalDateTime transactionDate) {
        this.id = id;
        this.merchantName = merchantName;
        this.installmentCount = installmentCount;
        this.installmentsPaid = 0;
        this.invoiceDateOfNextInstallment = invoiceDateOfNextInstallment;
        this.authorizationCode = authorizationCode;
        this.cardType = cardType;
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public Integer getInstallmentCount() {
        return installmentCount;
    }

    public void setInstallmentCount(Integer installmentCount) {
        this.installmentCount = installmentCount;
    }

    public Integer getInstallmentsPaid() {
        return installmentsPaid;
    }

    public void setInstallmentsPaid(Integer installmentsPaid) {
        this.installmentsPaid = installmentsPaid;
    }

    public YearMonth getInvoiceDateOfNextInstallment() {
        return invoiceDateOfNextInstallment;
    }

    public void setInvoiceDateOfNextInstallment(YearMonth invoiceDateOfNextInstallment) {
        this.invoiceDateOfNextInstallment = invoiceDateOfNextInstallment;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public CardType getCardType() {
        return cardType;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CardTransaction that = (CardTransaction) o;
        return Objects.equals(id, that.id) && Objects.equals(authorizationCode, that.authorizationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorizationCode);
    }
}
