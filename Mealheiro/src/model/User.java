package model;

import java.util.*;

/**
 *
 * @author ed
 */
public class User extends Observable {

    private static long idCounter = 0;

    private String id;
    private String username;
    private String email;
    private String password;
    private String netWorth;
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;

    public User(String username, String email, String password) {
        this.id = createID();
        this.username = username;
        this.email = email;
        this.password = password;
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

//    public void update(Observable o, Object arg) {
//        setChanged();
//        notifyObservers();
//    }
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        setChanged();
        notifyObservers();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        setChanged();
        notifyObservers();
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public Account getAccountById(String id) {
        for (Account acc : accounts) {
            if (acc.getId().equals(id)) {
                return acc;
            }
        }
        return null;
    }

    public Account getAccountByName(String accountName) {
        for (Account acc : accounts) {
            if (acc.getName().equals(accountName)) {
                return acc;
            }
        }
        return null;
    }

    public static synchronized String createID() {
        return String.valueOf(idCounter++);
    }

    @Override
    public String toString() {
        return id + "," + username + "," + email + "," + password + "," + netWorth + "," + accounts + "," + transactions;
    }

}
