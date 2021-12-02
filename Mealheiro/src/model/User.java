package model;

import java.util.*;

/**
 *
 * @author ed
 */
public class User extends Observable {

    private String username;
    private String email;
    private String password;
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public void update(Observable o, Object arg) {
//        setChanged();
//        notifyObservers();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    

}