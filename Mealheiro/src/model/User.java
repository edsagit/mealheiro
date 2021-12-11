package model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ed
 */
public class User extends Observable {

    private static long idCounter = 0;

    DecimalFormat df = new DecimalFormat("#.##");

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

    public String[] getAccountsNames() {
        String[] accountsNames = new String[accounts.size()];
        for (int i = 0; i < accounts.size(); i++) {
            accountsNames[i] = accounts.get(i).getName();
        }
        return accountsNames;
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

    public String getNetWorth() {
        Double totalNetWorth = 0.00;
        for (Account a : accounts) {
            if (a.getAccountType().equals(AccountType.ASSET)) {
                try {
                    Number n = NumberFormat.getNumberInstance().parse(a.getBalance());
                    totalNetWorth += n.doubleValue();
                } catch (ParseException ex) {
                    Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        return df.format(totalNetWorth);
    }
    
    public ArrayList getAllCategories(){
        ArrayList al = new ArrayList<>();
        for (Transaction t : transactions) {
            if (!al.contains(t.getCategory())) {
                al.add(t.getCategory());
            }
        }
        return al;
    }
    
    public String getCategoryTotalByCategory(String category) {
        Double totalCategory = 0.00;
        for (Transaction t : transactions) {
            if(t.getCategory().equals(category)) {
                try {
                    Number n = NumberFormat.getNumberInstance().parse(t.getAmount());
                    totalCategory += n.doubleValue();
                } catch (ParseException ex) {
                    Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return df.format(totalCategory);
    }

    public static synchronized String createID() {
        return String.valueOf(idCounter++);
    }

    @Override
    public String toString() {
        return id + "," + username + "," + email + "," + password + "," + netWorth + "," + accounts + "," + transactions;
    }

}
