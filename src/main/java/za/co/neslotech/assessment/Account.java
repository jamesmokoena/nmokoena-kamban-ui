package za.co.neslotech.assessment;

import java.util.List;
import java.util.Currency;

public class Account {
    private String accountId;
    private Currency currency;
    private double balance;

    public Account(String accountId, Currency currency, double balance) {
        this.accountId = accountId;
        this.currency = currency;
        this.balance = balance;
    }

    // Method to perform a transfer from this account to another account
    public void transfer(Account destinationAccount, double amount, List<ExchangeRate> exchangeRates) {
        // Check if source and destination accounts have the same currency
        if (this.currency.equals(destinationAccount.getCurrency())) {
            // No currency conversion needed, proceed with transfer
            if (this.balance >= amount) {
                this.balance -= amount;
                destinationAccount.balance += amount;
                // Create and persist transaction
                Transaction transaction = new Transaction("12345", this, destinationAccount, amount);
                // Persist transaction logic
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            // Currency conversion required
            double convertedAmount = convertCurrency(amount, destinationAccount.getCurrency(), exchangeRates);
            if (this.balance >= convertedAmount) {
                this.balance -= convertedAmount;
                destinationAccount.balance += amount;
                // Create and persist transaction
                Transaction transaction = new Transaction("12345", this, destinationAccount, amount);
                // Persist transaction logic
            } else {
                System.out.println("Insufficient funds after currency conversion.");
            }
        }

    }

    private double convertCurrency(double amount, Currency targetCurrency, List<ExchangeRate> exchangeRates) {
        String sourceCurrencyCode = this.currency.getCurrencyCode();
        for (ExchangeRate rate : exchangeRates) {
            if(rate.getBaseCurrency().equals(sourceCurrencyCode) &&
                    rate.getTargetCurrency().equals(targetCurrency.getCurrencyCode())) {
                return amount * rate.getRate();
            }
        }
        // If no exchange rate found, return -1 indicating failure
        return -1;
    }

    // Getters and setters
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

