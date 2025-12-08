package com.andre.projetobanco.Domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EntityListeners(AuditingEntityListener.class)
public abstract class Account implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String agency;
    private String accountNumber;
    private BigDecimal balance;
    private String transactionPinHash;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;

    public Account(Long id, User user, String agency, String accountNumber, BigDecimal balance, String transactionPinHash, Card card) {
        this.id = id;
        this.user = user;
        this.agency = agency;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionPinHash = transactionPinHash;
        this.card = card;
    }

    public Account() {

    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getTransactionPinHash() {
        return transactionPinHash;
    }

    public void setTransactionPinHash(String transactionPinHash) {
        this.transactionPinHash = transactionPinHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Card getCard() {
        return card;
    }

    public void deposit(BigDecimal amount){
        this.balance = this.balance.add(amount);
    }

    public boolean withdraw(BigDecimal amount){
        if(balance.subtract(amount).compareTo(BigDecimal.ZERO) <= 0){
            return false;
        }
        else {
            this.balance = this.balance.subtract(amount);
            return true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(user, account.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return accountNumber;
    }
}
