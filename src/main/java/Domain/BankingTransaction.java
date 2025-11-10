package Domain;

import Enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BankingTransaction {
    private final Long id;
    private final LocalDate dateTime;
    private final BigDecimal amount;
    private final TransactionType transactionType;
    private final String description;

    private final Account sourceAccount;
    private final Account destinationAccount;

    public BankingTransaction(Long id, LocalDate dateTime, BigDecimal amount, TransactionType transactionType, String description, Account sourceAccount, Account destinationAccount) {
        this.id = id;
        this.dateTime = dateTime;
        this.amount = amount;
        this.transactionType = transactionType;
        this.description = description;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getDescription() {
        return description;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }
}
