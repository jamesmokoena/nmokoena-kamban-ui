package za.co.neslotech.assessment;

import java.time.LocalDateTime;

public class TransactionLogEntry {
    private final String transactionId;
    private final LocalDateTime timestamp;
    private final String sourceAccountId;
    private final String destinationAccountId;
    private final double amount;
    private final String currency;
    private final boolean success;

    public TransactionLogEntry(String transactionId, LocalDateTime timestamp, String sourceAccountId, String destinationAccountId, double amount, String currency, boolean success) {
        this.transactionId = transactionId;
        this.timestamp = timestamp;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
        this.currency = currency;
        this.success = success;
    }

    // Getters and setters (omitted for brevity)

    @Override
    public String toString() {
        return "TransactionLogEntry{" +
                "transactionId='" + transactionId + '\'' +
                ", timestamp=" + timestamp +
                ", sourceAccountId='" + sourceAccountId + '\'' +
                ", destinationAccountId='" + destinationAccountId + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", success=" + success +
                '}';
    }
}