package application;

import controller.*;
import model.*;

/**
 *
 * @author ed
 */
public class Main {

    private UserList db;

    public Main() {
        db = new UserList();

        db.registerUser("ed", "person@mail.com", "asd", "CGD", "1000", "230");

        // Asset account
        Account wallet = new Account("Wallet", "0", AccountType.ASSET);
        wallet.setActive(false);
        db.getUserByUsername("ed").addAccount(wallet);

        // Revenue account
        Account revenueAcc = new Account("Google", "10000", AccountType.REVENUE);
        db.getUserByUsername("ed").addAccount(revenueAcc);
        
        
        System.out.println(db.getUserByUsername("ed").getAccountById("1"));
        Transaction revenueTra = new Transaction("1000.00", TransactionType.DEPOSIT, revenueAcc, db.getUserByUsername("ed").getAccountById("1"), "Salary", "Income");
        db.getUserByUsername("ed").getAccountById("1").addTransaction(revenueTra);
        revenueAcc.addTransaction(revenueTra);
        db.getUserByUsername("ed").addTransaction(revenueTra);

        // Expense account
        db.getUserByUsername("ed").addAccount(new Account("McDonalds", "0", AccountType.EXPENSE));

        // Liability account
        db.getUserByUsername("ed").addAccount(new Account("Casa", "0", AccountType.LIABILITY));

        MainController mc = new MainController();
        mc.setModel(db);
        mc.displayView();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main main = new Main();
    }

}
