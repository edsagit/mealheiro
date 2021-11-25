package model;

import java.util.*;

public class User extends Observable implements Observer {

    private String id;
    private String username;
    private String password;
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;

    public User(String username, String password) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        setChanged();
        notifyObservers();
    }

}
