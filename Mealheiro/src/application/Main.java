package application;

import application.model.TransactionType;
import application.model.User;
import application.model.Transaction;
import application.model.AccountType;
import application.model.Account;
import application.model.UserList;
import application.controller.MainController;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Arrays;

/**
 *
 * @author ed
 */
public class Main {

    private UserList db;

    public Main() {
        DecimalFormat df = new DecimalFormat("#.##");
        
        

        db = new UserList();

        db.registerUser("ed", "person@mail.com", "asd", "CGD", "1000", "200");
        db.registerUser("test", "person@mail.com", "asd", "CGD", "100", "2330");


        // variable to hold user 
        User user = db.getUserByUsername("ed");

        // Asset account
        Account accountAssetMain = db.getUserByUsername("ed").getAccounts().get(0);
        Account accountAssetSavings = db.getUserByUsername("ed").getAccounts().get(1);
        Account wallet = new Account("Wallet", "0", AccountType.ASSET);
        wallet.setActive(false);
        user.addAccount(wallet);

        // Instantiate Revenue accounts
        Account accountMicrosoft = new Account("Microsoft", "0", AccountType.REVENUE);
        Account accountCoinbase = new Account("Coinbase", "0", AccountType.REVENUE);
        // Add revenue accounts
        user.addAccount(accountMicrosoft);
        user.addAccount(accountCoinbase);

        // Instantiate Expense accounts
        Account accountEDP = new Account("EDP", "0", AccountType.EXPENSE);
        Account accountLandlord = new Account("Land lord", "0", AccountType.EXPENSE);
        Account accountBarFCTUC = new Account("Bar FCTUC", "0", AccountType.EXPENSE);
        Account accountMcdonalds = new Account("McDonalds", "0", AccountType.EXPENSE);
        Account accountContinente = new Account("Continente", "0", AccountType.EXPENSE);
        Account accountFeuvert = new Account("Feu Vert", "0", AccountType.EXPENSE);

        // Add accounts to user
        user.addAccount(accountEDP);
        user.addAccount(accountLandlord);
        user.addAccount(accountBarFCTUC);
        user.addAccount(accountMcdonalds);
        user.addAccount(accountContinente);
        user.addAccount(accountFeuvert);

        for (int i = 1; i < 10; i++) {
            // revenue from Microsoft
            Transaction revenueMicrosoft = new Transaction("1000.00", TransactionType.DEPOSIT, accountMicrosoft, accountAssetMain, "Microsoft revenue", "Salary", LocalDate.parse("2020-0" + i + "-01"));
            // add transaction to accounts
            accountAssetMain.addTransaction(revenueMicrosoft);
            accountMicrosoft.addTransaction(revenueMicrosoft);
            user.addTransaction(revenueMicrosoft);

            // simulate monthly savings transactions
            Transaction savings = new Transaction("200.00", TransactionType.TRANSFER, accountAssetMain, accountAssetSavings, "Save money", "Savings", LocalDate.parse("2020-0" + i + "-23"));
            // add transaction to accounts
            accountAssetMain.addTransaction(savings);
            accountAssetSavings.addTransaction(savings);
            user.addTransaction(savings);

            // instantiate new transactions
            Transaction expenseRent = new Transaction("300.0", TransactionType.WITHDRAWAL, accountAssetMain, accountLandlord, "Rent", "House", LocalDate.parse("2020-0" + i + "-03"));
            Transaction expenseEDP = new Transaction((df.format(Math.random() * 60 + 40)), TransactionType.WITHDRAWAL, accountAssetMain, accountEDP, "Eletricity and piped gas", "House", LocalDate.parse("2020-0" + i + "-03"));
            // add transaction rent to accounts
            accountAssetMain.addTransaction(expenseRent);
            accountLandlord.addTransaction(expenseRent);
            user.addTransaction(expenseRent);
            // add transaction edp to accounts
            accountAssetMain.addTransaction(expenseEDP);
            accountEDP.addTransaction(expenseEDP);
            user.addTransaction(expenseEDP);

            for (int j = 10; j < 12; j++) {

                // instantiate new transactions
                Transaction expenseBar = new Transaction((df.format(Math.random() * 5 + 1)), TransactionType.WITHDRAWAL, accountAssetMain, accountBarFCTUC, "Bar", "Coffee", LocalDate.parse("2020-0" + i + "-" + j));
                // add transaction to accounts
                accountAssetMain.addTransaction(expenseBar);
                accountBarFCTUC.addTransaction(expenseBar);
                user.addTransaction(expenseBar);

                // instantiate new transactions
                Transaction expenseMcdonalds = new Transaction((df.format(Math.random() * 10 + 1)), TransactionType.WITHDRAWAL, accountAssetMain, accountMcdonalds, "McDonalds", "Food and drink", LocalDate.parse("2020-0" + i + "-" + j));
                // add transaction to accounts
                accountAssetMain.addTransaction(expenseMcdonalds);
                accountMcdonalds.addTransaction(expenseMcdonalds);
                user.addTransaction(expenseMcdonalds);

                // instantiate new transactions
                Transaction expenseContinente = new Transaction((df.format(Math.random() * 30 + 10)), TransactionType.WITHDRAWAL, accountAssetMain, accountContinente, "Continente", "Groceries", LocalDate.parse("2020-0" + i + "-" + j));
                // add transaction to accounts
                accountAssetMain.addTransaction(expenseContinente);
                accountContinente.addTransaction(expenseContinente);
                user.addTransaction(expenseContinente);

            }
        }

        Transaction coinbaseRevenue = new Transaction("5000.00", TransactionType.DEPOSIT, accountCoinbase, accountAssetSavings, "Coinbase", "Investments", LocalDate.parse("2020-10-23"));
        accountAssetSavings.addTransaction(coinbaseRevenue);
        accountCoinbase.addTransaction(coinbaseRevenue);
        user.addTransaction(coinbaseRevenue);
        
        Transaction carExpense = new Transaction("3000.00", TransactionType.WITHDRAWAL, accountAssetMain, accountFeuvert, "Feu Vert", "Car", LocalDate.parse("2020-11-01"));
        accountAssetMain.addTransaction(carExpense);
        accountFeuvert.addTransaction(carExpense);
        user.addTransaction(carExpense);

        
        for (int i = 1; i < 10; i++) {
            // revenue from Microsoft
            Transaction revenueMicrosoft = new Transaction("1000.00", TransactionType.DEPOSIT, accountMicrosoft, accountAssetMain, "Microsoft revenue", "Salary", LocalDate.parse("2021-0" + i + "-01"));
            // add transaction to accounts
            accountAssetMain.addTransaction(revenueMicrosoft);
            accountMicrosoft.addTransaction(revenueMicrosoft);
            user.addTransaction(revenueMicrosoft);

            // simulate monthly savings transactions
            Transaction savings = new Transaction("200.00", TransactionType.TRANSFER, accountAssetMain, accountAssetSavings, "Save money", "Savings", LocalDate.parse("2021-0" + i + "-23"));
            // add transaction to accounts
            accountAssetMain.addTransaction(savings);
            accountAssetSavings.addTransaction(savings);
            user.addTransaction(savings);

            // instantiate new transactions
            Transaction expenseRent = new Transaction("300.0", TransactionType.WITHDRAWAL, accountAssetMain, accountLandlord, "Rent", "House", LocalDate.parse("2021-0" + i + "-03"));
            Transaction expenseEDP = new Transaction((df.format(Math.random() * 60 + 40)), TransactionType.WITHDRAWAL, accountAssetMain, accountEDP, "Eletricity and piped gas", "House", LocalDate.parse("2021-0" + i + "-03"));
            // add transaction rent to accounts
            accountAssetMain.addTransaction(expenseRent);
            accountLandlord.addTransaction(expenseRent);
            user.addTransaction(expenseRent);
            // add transaction edp to accounts
            accountAssetMain.addTransaction(expenseEDP);
            accountEDP.addTransaction(expenseEDP);
            user.addTransaction(expenseEDP);

            for (int j = 10; j < 12; j++) {

                // instantiate new transactions
                Transaction expenseBar = new Transaction((df.format(Math.random() * 5 + 1)), TransactionType.WITHDRAWAL, accountAssetMain, accountBarFCTUC, "Bar", "Coffee", LocalDate.parse("2021-0" + i + "-" + (j + (int)Math.random()*10+10)));
                // add transaction to accounts
                accountAssetMain.addTransaction(expenseBar);
                accountBarFCTUC.addTransaction(expenseBar);
                user.addTransaction(expenseBar);

                // instantiate new transactions
                Transaction expenseMcdonalds = new Transaction((df.format(Math.random() * 10 + 1)), TransactionType.WITHDRAWAL, accountAssetMain, accountMcdonalds, "McDonalds", "Food and drink", LocalDate.parse("2021-0" + i + "-" + (j + (int)Math.random()*20+2)));
                // add transaction to accounts
                accountAssetMain.addTransaction(expenseMcdonalds);
                accountMcdonalds.addTransaction(expenseMcdonalds);
                user.addTransaction(expenseMcdonalds);

                // instantiate new transactions
                Transaction expenseContinente = new Transaction((df.format(Math.random() * 30 + 10)), TransactionType.WITHDRAWAL, accountAssetMain, accountContinente, "Continente", "Groceries", LocalDate.parse("2021-0" + i + "-" + (j + (int)Math.random()*20+2)));
                // add transaction to accounts
                accountAssetMain.addTransaction(expenseContinente);
                accountContinente.addTransaction(expenseContinente);
                user.addTransaction(expenseContinente);

            }
        }

//        System.out.println(accountAssetSavings.getBalance());

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
