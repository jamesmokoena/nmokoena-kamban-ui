package za.co.neslotech.assessment;

import java.util.Currency;


public class Transaction {
    private String transactionId;
    private Account sourceAccount;
    private Account destinationAccount;
    private double amount;
    private Currency currency;


    public Transaction(String transactionId, Account sourceAccount, Account destinationAccount, double amount, Currency currency) {
        this.transactionId = transactionId;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.currency = currency;

    }

    public boolean execute() {
        // Check if source account has sufficient balance
        if (sourceAccount.getBalance() >= amount) {
            // Deduct amount from source account
            sourceAccount.withdraw(amount);

            // Convert amount to destination account currency if necessary
            if (!sourceAccount.getCurrency().equals(destinationAccount.getCurrency())) {
                double convertedAmount = sourceAccount.convertCurrency(amount, destinationAccount.getCurrency(), null);
                destinationAccount.deposit(convertedAmount);
            } else {
                // Deposit original amount to destination account
                destinationAccount.deposit(amount);
            }

            return true; // Transaction successful
        } else {
            return false; // Insufficient balance
        }
    }


        // Getters and setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



}
