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

    private static long idCounter = 0;

    private String id;
    private String amount;
    private TransactionType type;
    private Account sourceAccount;
    private Account destinationAccount;
    private String description;
    private Date date;

    public Transaction(String amount, TransactionType type, Account sourceAccount, Account destinationAccount) {
        this.id = createID();
        this.amount = amount;
        this.type = type;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.description = "";
        this.date = new Date();  
    }

    public static synchronized String createID() {
        return String.valueOf(idCounter++);
    }

}
