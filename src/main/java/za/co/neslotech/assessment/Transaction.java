package za.co.neslotech.assessment;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;


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

        // Log the transaction
        logTransaction();

        // Check if source account has sufficient balance
        if (sourceAccount.getBalance() < amount) {
            System.out.println("Insufficient balance in the source account.");
            return false;
        }

        // Check if source and destination accounts have the same currency
        if (sourceAccount.getCurrency().equals(destinationAccount.getCurrency())) {
            // If currencies are the same, simply transfer the amount
            sourceAccount.withdraw(amount);
            destinationAccount.deposit(amount);
            return true;
        }

        // Retrieve exchange rate from the API
        APIIntegration exchangeRateAPI = new APIIntegration();
        double exchangeRate = exchangeRateAPI.getExchangeRates().size();

        // Convert the amount to the destination currency
        double convertedAmount = amount * exchangeRate;

        // Withdraw the original amount from the source account
        sourceAccount.withdraw(amount);

        // Deposit the converted amount into the destination account
        destinationAccount.deposit(convertedAmount);

        return true;
    }

    private void logTransaction() {
        // Create a transaction log entry
        TransactionLogEntry logEntry = new TransactionLogEntry(
                transactionId,
                LocalDateTime.now(),
                sourceAccount.getAccountId(),
                destinationAccount.getAccountId(),
                amount,
                currency.getCurrencyCode(),
                false // Assume transaction is unsuccessful by default
        );

        // Write the log entry to a file or database
        writeToTransactionLog(logEntry);
    }
    private void writeToTransactionLog(TransactionLogEntry logEntry) {
        // Define the path to the transaction log file
        String logFilePath = "transaction_log.txt";

        // Open the file for appending (create the file if it doesn't exist)
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
            // Write the log entry to the file
            writer.println(logEntry.toString());
        } catch (IOException e) {
            System.err.println("Error writing to transaction log file: " + e.getMessage());
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
