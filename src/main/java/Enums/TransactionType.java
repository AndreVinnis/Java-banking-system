package Enums;

public enum TransactionType {

    // Cash Flow
    DEPOSIT("Deposit", false),
    WITHDRAWAL("Withdrawal", false),
    // Person to Person (P2P)
    TRANSFER_OUT("Transfer Out", false),
    TRANSFER_IN("Transfer In", true),
    // Card Transactions
    CARD_PURCHASE_DEBIT("Debit Purchase", false),
    CARD_PURCHASE_CREDIT("Credit Purchase", false),
    // Payments
    BILL_PAYMENT("Bill Payment", false),
    // Fees
    FEE_CHARGE("Fee Charge", false),
    INTEREST_INCOME("Interest Income", true),
    INTEREST_CHARGE("Interest Charge", false);

    private final String description;
    private final boolean isCredit;

    TransactionType(String description, boolean isCredit) {
        this.description = description;
        this.isCredit = isCredit;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCredit() {
        return isCredit;
    }
}
