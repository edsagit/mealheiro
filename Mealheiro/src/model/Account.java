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
public class Account {
    
    private String name;
//    private String currency;
    private String iban;
    private String bic;
    private String accountNumber;
    private String balance;
    private Date openingDate;
    private AccountType accountType;

    public Account(String name) {
        this.name = name;
        this.iban = "";
        this.bic = "";
        this.accountNumber = "";
        this.balance = "";
        this.openingDate = Date.from(Instant.MIN);
        this.accountType = AccountType.ASSET;
    }

    public Account(String name, Date openingDate) {
        this.name = name;
        this.iban = "";
        this.bic = "";
        this.accountNumber = "";
        this.balance = "";
        this.openingDate = openingDate;
        this.accountType = AccountType.ASSET;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    
    
}
