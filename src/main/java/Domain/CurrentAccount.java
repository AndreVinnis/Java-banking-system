package Domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CurrentAccount extends Account {
    private BigDecimal overdrawLimit;
    private BigDecimal maintenanceFee;

    public CurrentAccount(Long id, User user, String agency, String accountNumber, BigDecimal balance, String transactionPinHash, LocalDateTime createdAt, BigDecimal overdrawLimit, BigDecimal maintenanceFee) {
        super(id, user, agency, accountNumber, balance, transactionPinHash, createdAt);
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
