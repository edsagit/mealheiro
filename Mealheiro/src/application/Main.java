package application;

import controller.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import model.*;

/**
 *
 * @author ed
 */
public class Main {

    private UserList db;

    public Main() {
        DecimalFormat df = new DecimalFormat("#.##");
        
        db = new UserList();

        db.registerUser("ed", "person@mail.com", "asd", "CGD", "1000", "230");
        db.registerUser("test", "person@mail.com", "asd", "CGD", "100", "2330");

        // Asset account
        Account wallet = new Account("Wallet", "0", AccountType.ASSET);
        wallet.setActive(false);
        db.getUserByUsername("ed").addAccount(wallet);

        // Instantiate Revenue accounts
        Account accountMicrosoft = new Account("Microsoft", "0", AccountType.REVENUE);
        // Add revenue accounts
        db.getUserByUsername("ed").addAccount(accountMicrosoft);

        for (int i = 1; i < 10; i++) {
            Transaction revenueMicrosoft = new Transaction("1000.00", TransactionType.DEPOSIT, accountMicrosoft, db.getUserByUsername("ed").getAccountById("1"), "Microsoft revenue", "Salary", LocalDate.parse("2020-0" + i + "-23"));

            db.getUserByUsername("ed").getAccountById("1").addTransaction(revenueMicrosoft);
            accountMicrosoft.addTransaction(revenueMicrosoft);
            db.getUserByUsername("ed").addTransaction(revenueMicrosoft);
        }

        // Instantiate Expense accounts
        Account accountEDP = new Account("EDP", "0", AccountType.EXPENSE);
        Account accountLandlord = new Account("Land lord", "0", AccountType.EXPENSE);
        Account accountBarFCTUC = new Account("Bar FCTUC", "0", AccountType.EXPENSE);
        Account accountMcdonalds = new Account("McDonalds", "0", AccountType.EXPENSE);
        Account accountContinente = new Account("Continente", "0", AccountType.EXPENSE);
        
        // Add accounts to user
        db.getUserByUsername("ed").addAccount(accountEDP);
        db.getUserByUsername("ed").addAccount(accountLandlord);
        db.getUserByUsername("ed").addAccount(accountBarFCTUC);
        db.getUserByUsername("ed").addAccount(accountMcdonalds);
        db.getUserByUsername("ed").addAccount(accountContinente);
        // Create transactions
        for (int i = 1; i < 10; i++) {
            // instantiate new transactions
            Transaction expenseRent = new Transaction("300.0", TransactionType.WITHDRAWAL, db.getUserByUsername("ed").getAccountById("1"), accountLandlord, "Rent", "House", LocalDate.parse("2020-0" + i + "-03"));
            Transaction expenseEDP = new Transaction((df.format(Math.random()*60 + 40)), TransactionType.WITHDRAWAL, db.getUserByUsername("ed").getAccountById("1"), accountEDP, "Eletricity and piped gas", "House", LocalDate.parse("2020-0" + i + "-03"));
            // add transaction rent to accounts
            db.getUserByUsername("ed").getAccountById("1").addTransaction(expenseRent);
            accountMicrosoft.addTransaction(expenseRent);
            db.getUserByUsername("ed").addTransaction(expenseRent);
            // add transaction edp to accounts
            db.getUserByUsername("ed").getAccountById("1").addTransaction(expenseEDP);
            accountMicrosoft.addTransaction(expenseEDP);
            db.getUserByUsername("ed").addTransaction(expenseEDP);
            
            int interval = (int)(Math.random()*19);
            for (int j = 10; j < Math.random()*interval+11 ; j++) {
                
                // instantiate new transactions
                Transaction expenseBar = new Transaction((df.format(Math.random()*5 + 1)), TransactionType.WITHDRAWAL, db.getUserByUsername("ed").getAccountById("1"), accountBarFCTUC, "Bar", "Coffee", LocalDate.parse("2020-0" + i + "-" + j));
                // add transaction to accounts
                db.getUserByUsername("ed").getAccountById("1").addTransaction(expenseBar);
                accountMicrosoft.addTransaction(expenseBar);
                db.getUserByUsername("ed").addTransaction(expenseBar);
                
                // instantiate new transactions
                Transaction expenseMcdonalds = new Transaction((df.format(Math.random()*10 + 1)), TransactionType.WITHDRAWAL, db.getUserByUsername("ed").getAccountById("1"), accountMcdonalds, "McDonalds", "Food and drink", LocalDate.parse("2020-0" + i + "-" + (j+(int)Math.random()*7)));
                // add transaction to accounts
                db.getUserByUsername("ed").getAccountById("1").addTransaction(expenseMcdonalds);
                accountMicrosoft.addTransaction(expenseMcdonalds);
                db.getUserByUsername("ed").addTransaction(expenseMcdonalds);
                                
                // instantiate new transactions
                Transaction expenseContinente = new Transaction((df.format(Math.random()*30 + 10)), TransactionType.WITHDRAWAL, db.getUserByUsername("ed").getAccountById("1"), accountContinente, "Continente", "Groceries", LocalDate.parse("2020-0" + i + "-" + (j+4)));
                // add transaction to accounts
                db.getUserByUsername("ed").getAccountById("1").addTransaction(expenseContinente);
                accountMicrosoft.addTransaction(expenseContinente);
                db.getUserByUsername("ed").addTransaction(expenseContinente);
                
            }
        }

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
