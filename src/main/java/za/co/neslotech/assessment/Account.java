package za.co.neslotech.assessment;

import java.util.List;
import java.util.Currency;
import java.util.Map;

public class Account {
    private String accountId;
    private Currency currency;
    private double balance;


    public Account(String accountId, Currency currency, double balance) {
        this.accountId = accountId;
        this.currency = currency;
        this.balance = balance;

    }

    public boolean withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            return true; // Withdrawal successful
        } else {
            return false; // Insufficient balance
        }
    }
    public void deposit(double amount) {
        this.balance += amount;
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
                Transaction transaction = new Transaction("12345", this, destinationAccount, amount,currency);
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
                Transaction transaction = new Transaction("12345", this, destinationAccount, amount,currency);
                // Persist transaction logic
            } else {
                System.out.println("Insufficient funds after currency conversion.");
            }
        }

    }

    public double convertCurrency(double amount, Currency targetCurrency, List<ExchangeRate> exchangeRates) {
        if (this.currency.equals(targetCurrency)) {
            return amount; // No need for conversion if currencies are the same
        }

        // Retrieve exchange rate from API
        APIIntegration exchangeRateAPI = new APIIntegration();
        Map<String, Double> latestRates = exchangeRateAPI.getExchangeRates();

        // Find exchange rate for target currency
        Double exchangeRate = latestRates.get(targetCurrency.getCurrencyCode());
        if (exchangeRate != null) {
            return amount * exchangeRate;
        } else {
            System.out.println("Exchange rate not available for conversion.");
            return -1; // Indicate failure
        }
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

