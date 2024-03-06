package za.co.neslotech.assessment;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private String country;
    private List<Account> accounts;

    public User(String userId, String name, String country) {
        this.userId = userId;
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
