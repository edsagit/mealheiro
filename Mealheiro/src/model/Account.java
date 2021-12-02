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
    
    
}
