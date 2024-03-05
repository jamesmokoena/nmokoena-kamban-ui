package za.co.neslotech.assessment;

import java.util.ArrayList;


public class User {
    private String userId;
    private String name;
    private List<Account> accounts;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.accounts = new ArrayList<>();
    }


    public void addAccount(Account account) {
        accounts.add(account);
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}

