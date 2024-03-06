package za.co.neslotech.assessment;

import java.util.Currency;
import java.util.Locale;

public class Central_class{
    public static void main(String[] args) {
        // Create some sample users
        User user1 = new User("1", "Alice", "USA");
        User user2 = new User("2", "Bob", "UK");

        // Create some sample accounts for the users
        Account account1 = new Account("001", Currency.getInstance(Locale.US), 1000);
        Account account2 = new Account("002",  Currency.getInstance(Locale.US), 500);
        Account account3 = new Account("003",  Currency.getInstance(Locale.UK), 800);

        // Add the accounts to the users
        user1.addAccount(account1);
        user1.addAccount(account2);
        user2.addAccount(account3);

        // Perform some sample transactions
        Transaction transaction1 = new Transaction("T001", account1, account2, 200, Currency.getInstance("USD"));
        boolean success1 = transaction1.execute();

        Transaction transaction2 = new Transaction("T002", account1, account3, 300, Currency.getInstance("USD"));
        boolean success2 = transaction2.execute();

        // Check if the transactions were successful
        if (success1) {
            System.out.println("Transaction 1 successful!");
        } else {
            System.out.println("Transaction 1 failed: Insufficient balance or accounts don't belong to the same user.");
        }

        if (success2) {
            System.out.println("Transaction 2 successful!");
        } else {
            System.out.println("Transaction 2 failed: Insufficient balance or accounts don't belong to the same user.");
        }

        // Print the updated balances after the transactions
        System.out.println("Updated balance for account1 (USD): " + account1.getBalance());
        System.out.println("Updated balance for account2 (EUR): " + account2.getBalance());
        System.out.println("Updated balance for account3 (GBP): " + account3.getBalance());
    }
}
