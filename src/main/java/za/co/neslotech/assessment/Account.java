package za.co.neslotech.assessment;

public class Account {
    private String accountId;
    private String currency;
    private double balance;

    public Account(String accountId, String currency, double balance) {
        this.accountId = accountId;
        this.currency = currency;
        this.balance = balance;
    }

    // Method to perform a transfer from this account to another account
    public void transfer(Account destinationAccount, double amount) {

    }

    // Getters and setters
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

