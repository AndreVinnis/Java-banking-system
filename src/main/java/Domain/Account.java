package Domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Account {
    private Long id;
    private User user;
    private String agency;
    private String accountNumber;
    private BigDecimal balance;
    private String transactionPinHash;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Account() {
    }

    public Account(Long id, User user, String agency, String accountNumber, BigDecimal balance, String transactionPinHash, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.agency = agency;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionPinHash = transactionPinHash;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
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

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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
}
