package application.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ed
 */
public class Transaction extends Observable {

    DecimalFormat df = new DecimalFormat("#.##");

    private UUID id;
    private String amount;
    private TransactionType type;
    private Account sourceAccount;
    private Account destinationAccount;
    private String description;
    private String category;
    private LocalDate date;

    public Transaction(String amount, TransactionType type, Account sourceAccount, Account destinationAccount,
            String description, String category, LocalDate date) {

        this.id = UUID.randomUUID();
        this.amount = amount;
        this.type = type;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.description = description;
        this.category = category;
        this.date = date;
    }

    public void execute() {

//        System.out.println("EXECUTED TRANSACTION");
//        System.out.println("------- AMOUNT: " + amount);
//        System.out.println("------- FROM " + sourceAccount.getName());
//        System.out.println("------- TO " + destinationAccount.getName());
        try {
            // declare variables to hold account balances and amount
            Number initialSourceBalance = null;
            Number initialDestinationBalance = null;
            Number amt = null;

            // get balances from both accounts and the amount needed for the transaction
            initialSourceBalance = NumberFormat.getNumberInstance().parse(sourceAccount.getBalance());
            initialDestinationBalance = NumberFormat.getNumberInstance().parse(destinationAccount.getBalance());
            amt = NumberFormat.getNumberInstance().parse(amount);

            // remove amount from sourceAccount and add amout to destinationAccount
            Double resultFinalSourceBalance = initialSourceBalance.doubleValue() - amt.doubleValue();
            Double resultFinalDestinationBalance = initialDestinationBalance.doubleValue() + amt.doubleValue();

            // properly format the result, rounding to 2 decimal places
            df.format(resultFinalSourceBalance);
            df.format(resultFinalDestinationBalance);

            // this.date.toString() + " - " + sourceAccount.getBalance());
            sourceAccount.addBalanceHistory(this.date, Double.toString(resultFinalSourceBalance));
            destinationAccount.addBalanceHistory(this.date, Double.toString(resultFinalDestinationBalance));

            // convert double to string and set balances
            sourceAccount.setBalance(Double.toString(resultFinalSourceBalance));
            destinationAccount.setBalance(Double.toString(resultFinalDestinationBalance));
        } catch (ParseException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
        setChanged();
        notifyObservers();
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
        setChanged();
        notifyObservers();
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
        setChanged();
        notifyObservers();
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
        setChanged();
        notifyObservers();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        setChanged();
        notifyObservers();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        setChanged();
        notifyObservers();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        setChanged();
        notifyObservers();
    }

}
