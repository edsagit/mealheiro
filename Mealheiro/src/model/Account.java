/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.Instant;
import java.util.*;

/**
 *
 * @author ed
 */
public class Account extends Observable {

    private static long idCounter = 0;

    private String id;
    private String name;
//    private String currency;
    private String iban;
    private String bic;
    private String accountNumber;
    private String balance;
    private Date openingDate;
    private AccountType accountType;
    private Boolean active;
    private ArrayList<Transaction> transactions;

    public Account(String name, AccountType type) {
        this.id = createID();
        this.name = name;
        this.iban = "";
        this.bic = "";
        this.accountNumber = "";
        this.balance = "";
        this.openingDate = Date.from(Instant.MIN);
        this.accountType = type;
        this.transactions = new ArrayList<>();
    }

    public Account(String name, String balance, AccountType type) {
        this.id = createID();
        this.name = name;
        this.iban = "";
        this.bic = "";
        this.accountNumber = "";
        this.balance = balance;
        this.openingDate = openingDate;
        this.accountType = type;
        this.transactions = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers();
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
        setChanged();
        notifyObservers();
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
        setChanged();
        notifyObservers();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        setChanged();
        notifyObservers();
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
        setChanged();
        notifyObservers();
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
        setChanged();
        notifyObservers();
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
        setChanged();
        notifyObservers();
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
        setChanged();
        notifyObservers();
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        setChanged();
        notifyObservers();
    }

    public static synchronized String createID() {
        return String.valueOf(idCounter++);
    }

}
