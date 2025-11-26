package com.andre.projetobanco.Domain;

import com.andre.projetobanco.Enums.Flag;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private String cvv;
    private Flag flag;
    private YearMonth validity;
    private String pinCardHash;
    private BigDecimal creditLimit;
    private BigDecimal currentBalance;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Invoice> invoices = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Card> cardTransactions = new HashSet<>();

    public Card() {
    }

    public Card(Long id, String cardNumber, String cvv, Flag flag, YearMonth validity, String pinCardHash) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.flag = flag;
        this.validity = validity;
        this.pinCardHash = pinCardHash;
        this.currentBalance = BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public Flag getFlag() {
        return flag;
    }

    public YearMonth getValidity() {
        return validity;
    }

    public void setValidity(YearMonth validity) {
        this.validity = validity;
    }

    public String getPinCardHash() {
        return pinCardHash;
    }

    public void setPinCardHash(String pinCardHash) {
        this.pinCardHash = pinCardHash;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public Set<Card> getCardTransactions() {
        return cardTransactions;
    }

   public BigDecimal getAvailableLimit() {
        if(this.creditLimit == null){
            return BigDecimal.ZERO;
        }
        return creditLimit.subtract(this.currentBalance);
   }

    public boolean authorizeCreditUse(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) <= 0){
            return false;
        }

        if(amount.compareTo(getAvailableLimit()) <= 0){
            currentBalance = currentBalance.add(amount);
            return true;
        }
        else{
            return false;
        }
    }

    public void processPayment(BigDecimal amount){
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive.");
        }

        if (amount.compareTo(currentBalance) >= 0) {
            throw new IllegalArgumentException("The stated value is greater than the required value..");
        }

        this.currentBalance = this.currentBalance.subtract(amount);
    }
}
