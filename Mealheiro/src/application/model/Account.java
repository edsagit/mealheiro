package application.model;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author ed
 */
public class Account extends Observable {

    DecimalFormat df = new DecimalFormat("#.##");

    private UUID id;
    private String name;
    private String iban;
    private String bic;
    private String accountNumber;
    private String balance;
    private Date openingDate;
    private AccountType accountType;
    private Boolean active;
    private ArrayList<Transaction> transactions;
    private Map balanceHistory;

    public Account(String name, AccountType type) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.iban = "";
        this.bic = "";
        this.accountNumber = "";
        this.balance = "";
        this.openingDate = new Date();
        this.accountType = type;
        this.transactions = new ArrayList<>();
        this.balanceHistory = new TreeMap<LocalDate, String>();
    }

    public Account(String name, String balance, AccountType type) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.iban = "";
        this.bic = "";
        this.accountNumber = "";
        this.balance = balance;
        this.openingDate = new Date();
        this.accountType = type;
        this.transactions = new ArrayList<>();
        this.balanceHistory = new TreeMap<LocalDate, String>();
    }

    public Account(String name, String balance, AccountType type, String iban, String bic, String number) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.iban = iban;
        this.bic = bic;
        this.accountNumber = number;
        this.balance = balance;
        this.openingDate = new Date();
        this.accountType = type;
        this.transactions = new ArrayList<>();
        this.balanceHistory = new TreeMap<LocalDate, String>();
    }

    public String getId() {
        return id.toString();
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
        return df.format(Double.valueOf(this.balance));
    }

    public void setBalance(String balance) {
        this.balance = df.format(Double.valueOf(balance));
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

//    public void removeTransaction(Transaction transaction) {
//        if (transactions.contains(transaction)) {
//            transactions.remove(transaction);
//            setChanged();
//            notifyObservers();
//        }
//    }
//
//    public void addTransaction(Transaction transaction) {
//        this.transactions.add(transaction);
//        setChanged();
//        notifyObservers();
//    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public Map getBalanceHistory() {
        return balanceHistory;
    }

    public void addBalanceHistory(LocalDate date, String balance) {
        balanceHistory.put(date, balance);
    }

}
