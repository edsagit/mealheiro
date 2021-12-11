package application.model;

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

    DecimalFormat df = new DecimalFormat("#.##");

    private UUID id;
    private String username;
    private String email;
    private String password;
    private String netWorth;
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;

    public User(String username, String email, String password) {
        this.id = UUID.randomUUID();
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
    
    /**
     * 
     * @return String id
     */
    public String getId() {
        return id.toString();
    }
    
    /**
     * 
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param transaction Transaction - Transaction to add to user transactions
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        setChanged();
        notifyObservers();
    }

    /**
     * 
     * @param account Account - Account to add to user accounts
     */
    public void addAccount(Account account) {
        accounts.add(account);
        setChanged();
        notifyObservers();
    }
    
    /**
     * 
     * @return String[] accountsNames - Array of String holding all user account names
     */
    public String[] getAccountsNames() {
        String[] accountsNames = new String[accounts.size()];
        for (int i = 0; i < accounts.size(); i++) {
            accountsNames[i] = accounts.get(i).getName();
        }
        return accountsNames;
    }
    
    /**
     * 
     * @return ArrayList accounts - ArrayList with all user accounts
     */
    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    
    /**
     * 
     * @return ArrayList transactions - ArrayList with all user transactions
     */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    
    /**
     * 
     * @param id String
     * @return Account acc - Return Account acc if Account found with id equals to String id
     */
    public Account getAccountById(String id) {
        for (Account acc : accounts) {
            if (acc.getId().equals(id)) {
                return acc;
            }
        }
        return null;
    }

    /**
     * 
     * @param accountName String
     * @return Account acc - Return Account acc if Account found with account name equals to String accountName
     */
    public Account getAccountByName(String accountName) {
        for (Account acc : accounts) {
            if (acc.getName().equals(accountName)) {
                return acc;
            }
        }
        return null;
    }
    
    /**
     * 
     * @return String totalNetWorth - String with the sum of the balances from all user asset accounts
     */
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
    
    /**
     * 
     * @return ArrayList - Returns ArrayList of Strings with only one instance from each category found in user transactions
     */
    public ArrayList getAllCategories(){
        ArrayList al = new ArrayList<>();
        transactions.stream().filter(t -> (!al.contains(t.getCategory()))).forEachOrdered(t -> {
            al.add(t.getCategory());
        });
        return al;
    }
    
    /**
     * 
     * @param category String
     * @return String totalCategory - String with the sum from all the transactions found with category equals to String category
     */
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

    /**
     * 
     * @return String 
     */
    @Override
    public String toString() {
        return id + "," + username + "," + email + "," + password + "," + netWorth + "," + accounts + "," + transactions;
    }

}
