/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ed
 */
public class Transaction extends Observable {

    private static long idCounter = 0;

    private String id;
    private String amount;
    private TransactionType type;
    private Account sourceAccount;
    private Account destinationAccount;
    private String description;
    private String category;
    private Date date;

    public Transaction(String amount, TransactionType type, Account sourceAccount, Account destinationAccount, String description, String category) {
        this.id = createID();
        this.amount = amount;
        this.type = type;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.description = description;
        this.category = category;
        this.date = new Date();

        operationTransaction(sourceAccount, destinationAccount, amount);
    }

    public void operationTransaction(Account sourceAccount, Account destinationAccount, String amount) {
        Number initialSourceBalance = null;
        Number initialDestinationBalance = null;
        Number amt = null;
        try {
            initialSourceBalance = NumberFormat.getNumberInstance().parse(sourceAccount.getBalance());
            initialDestinationBalance = NumberFormat.getNumberInstance().parse(destinationAccount.getBalance());
            amt = NumberFormat.getNumberInstance().parse(amount);
            System.out.print(initialSourceBalance.doubleValue());
            System.out.print(initialDestinationBalance.doubleValue());
            System.out.print(amt.doubleValue());
        } catch (ParseException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }

        Double resultFinalSourceBalance = initialSourceBalance.doubleValue() - amt.doubleValue();
        Double resultFinalDestinationBalance = initialDestinationBalance.doubleValue() + amt.doubleValue();
        
        String FinalSourceBalance = Double.toString(resultFinalSourceBalance);
        String FinalDestinationBalance = Double.toString(resultFinalDestinationBalance);

        sourceAccount.setBalance(FinalSourceBalance);
        destinationAccount.setBalance(FinalDestinationBalance);
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    

    public static synchronized String createID() {
        return String.valueOf(idCounter++);
    }

}
