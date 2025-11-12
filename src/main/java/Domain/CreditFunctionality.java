package Domain;

import java.math.BigDecimal;

public class CreditFunctionality {
    private BigDecimal creditLimit;
    private BigDecimal currentBalance;


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

    public Boolean processCredit(BigDecimal amount){
        if(creditLimit.subtract(amount).intValue() >= 0 && creditLimit.subtract(currentBalance).compareTo(amount) >= 0){
            currentBalance = currentBalance.add(amount);
            return true;
        }
        return false;
    }
}
