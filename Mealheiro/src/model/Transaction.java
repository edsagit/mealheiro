/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.*;
import java.util.*;

/**
 *
 * @author ed
 */
public class Transaction {
    
    private String id;
    private String amount;
    private TransactionType type;
    private Account sourceAccount;
    private Account destinationAccount;
    private String description;
    private Date date;

    public Transaction(String amount, TransactionType type, Account sourceAccount, Account destinationAccount) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.type = type;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.description = "";
        this.date = Date.from(Instant.MIN);
    }
    
    
    
}
