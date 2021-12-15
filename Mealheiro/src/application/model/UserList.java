package application.model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author ed
 */
public class UserList extends Observable implements Observer {

    private ArrayList<User> users;
    private User loggedInUser = null;

    public UserList() {
        this.users = new ArrayList<>();
    }

    /**
     *
     * @param username String
     * @return Boolean - Return true if match found, false if no match found for
     * String username in ArrayList users
     */
    public Boolean usernameExists(String username) {
        return users.stream().anyMatch(u -> (u.getUsername().equals(username)));
    }

    /**
     *
     * @param username String
     * @return User user - Return User user if User found with correspondent
     * String username, if not return null
     */
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     *
     * @param username String
     * @param password String
     * @return Boolean - Return true if match found, false if not match found
     * for String username and password
     */
    public Boolean loginUser(String username, String password) {
        return users.stream().anyMatch(user -> (user.getUsername().equals(username) && user.getPassword().equals(password)));
    }

    /**
     *
     * @param username String - Value for username
     * @param email String - Value for email
     * @param password String - Value for password
     * @param bankName String - Value for bank name
     * @param defaultBalance String - Value for default account balance
     * @param savingsBalance String - Value for savings account balance
     */
    public void registerUser(String username, String email, String password, String bankName, String defaultBalance, String savingsBalance) {
        if (!usernameExists(username)) {
            System.out.println("UserList Model: updated");

            // temporary User variable to hold the new user
            User tmpUser = new User(username, email, password);

            // instantiate default asset account
            Account sourceOpeningBalance = new Account("Initial balance for " + bankName + " account", defaultBalance, AccountType.OPENING);
            Account destinationOpeningBalance = new Account(bankName, "0", AccountType.ASSET); // destination account
            // set account active
            destinationOpeningBalance.setActive(true);
            // add destination account to user
            tmpUser.addAccount(destinationOpeningBalance);
            // instantiate transaction
            Transaction transactionOpeningBalance = new Transaction(defaultBalance, TransactionType.OPENING_BALANCE, sourceOpeningBalance, destinationOpeningBalance, "Initial balance for " + bankName + " account", "Opening balance", LocalDate.parse("2020-01-23"));
            // add transaction, also executes the transaction
            tmpUser.addTransaction(transactionOpeningBalance);

            // instantiate default asset savings account
            Account sourceOpeningSavingBalance = new Account("Initial balance for " + bankName + " savings account", savingsBalance, AccountType.OPENING);
            Account destinationOpeningSavingsBalance = new Account(bankName + " savings account", "0", AccountType.ASSET);
            // set account active
            destinationOpeningSavingsBalance.setActive(true);
            // add destination account to user
            tmpUser.addAccount(destinationOpeningSavingsBalance);
            // instantiate transaction
            Transaction transactionSavingsBalance = new Transaction(savingsBalance, TransactionType.OPENING_BALANCE, sourceOpeningSavingBalance, destinationOpeningSavingsBalance, "Initial balance for " + bankName + " savings account", "Opening balance", LocalDate.parse("2020-01-23"));
            // add transaction, also executes the transaction
            tmpUser.addTransaction(transactionSavingsBalance);

            // add observer to user
            tmpUser.addObserver(this);
            // add user to ArrayList users
            this.users.add(tmpUser);
            setChanged();
            notifyObservers();
        }
    }

    /**
     *
     * @return ArrayList users - Returns ArrayList with all the Users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     *
     * @return User loggedInUser - Return User loggedInUser if user is logged
     * in, or null if user not logged in
     */
    public User getLoggedInUser() {
        if (this.loggedInUser != null) {
            return this.loggedInUser;
        } else {
            return null;
        }
    }

    /**
     *
     * @param user User - Set logged in user to User user
     */
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
        setChanged();
        notifyObservers("session_setted");
    }
    
    public void logoutUser() {
        this.loggedInUser = null;
        setChanged();
        notifyObservers("session_unsetted");
    }

    /**
     *
     * @param o Observable
     * @param arg Object
     */
    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }

    /**
     * Instantiate dummy data as content for the application
     */
    public void instantiateDummyData() {
        DecimalFormat df = new DecimalFormat("#.##");

        // register new user
        registerUser("ed", "person@mail.com", "asd", "CGD", "1000", "200");

        // variable to hold user 
        User user = getUserByUsername("ed");

        // Asset account
        Account accountAssetMain = getUserByUsername("ed").getAccounts().get(0);
        Account accountAssetSavings = getUserByUsername("ed").getAccounts().get(1);
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
            user.addTransaction(revenueMicrosoft);

            // simulate monthly savings transactions
            Transaction savings = new Transaction("200.00", TransactionType.TRANSFER, accountAssetMain, accountAssetSavings, "Save money", "Savings", LocalDate.parse("2020-0" + i + "-23"));

            // add transaction to accounts
            user.addTransaction(savings);

            // instantiate new transactions
            Transaction expenseRent = new Transaction("300.0", TransactionType.WITHDRAWAL, accountAssetMain, accountLandlord, "Rent", "House", LocalDate.parse("2020-0" + i + "-03"));
            Transaction expenseEDP = new Transaction((df.format(Math.random() * 60 + 40)), TransactionType.WITHDRAWAL, accountAssetMain, accountEDP, "Eletricity and piped gas", "House", LocalDate.parse("2020-0" + i + "-03"));

            // add transaction rent to accounts
            user.addTransaction(expenseRent);

            // add transaction edp to accounts
            user.addTransaction(expenseEDP);

            for (int j = 10; j < 12; j++) {

                // instantiate new transactions
                Transaction expenseBar = new Transaction((df.format(Math.random() * 5 + 1)), TransactionType.WITHDRAWAL, accountAssetMain, accountBarFCTUC, "Bar", "Coffee", LocalDate.parse("2020-0" + i + "-" + j));

                // add transaction to accounts
                user.addTransaction(expenseBar);

                // instantiate new transactions
                Transaction expenseMcdonalds = new Transaction((df.format(Math.random() * 10 + 1)), TransactionType.WITHDRAWAL, accountAssetMain, accountMcdonalds, "McDonalds", "Food and drink", LocalDate.parse("2020-0" + i + "-" + j));

                // add transaction to accounts
                user.addTransaction(expenseMcdonalds);

                // instantiate new transactions
                Transaction expenseContinente = new Transaction((df.format(Math.random() * 30 + 10)), TransactionType.WITHDRAWAL, accountAssetMain, accountContinente, "Continente", "Groceries", LocalDate.parse("2020-0" + i + "-" + j));

                // add transaction to accounts
                user.addTransaction(expenseContinente);

            }
        }

        Transaction coinbaseRevenue = new Transaction("5000.00", TransactionType.DEPOSIT, accountCoinbase, accountAssetSavings, "Coinbase", "Investments", LocalDate.parse("2020-10-23"));

        user.addTransaction(coinbaseRevenue);

        Transaction carExpense = new Transaction("3000.00", TransactionType.WITHDRAWAL, accountAssetMain, accountFeuvert, "Feu Vert", "Car", LocalDate.parse("2020-11-01"));

        user.addTransaction(carExpense);

        for (int i = 1; i < 10; i++) {
            // revenue from Microsoft
            Transaction revenueMicrosoft = new Transaction("1000.00", TransactionType.DEPOSIT, accountMicrosoft, accountAssetMain, "Microsoft revenue", "Salary", LocalDate.parse("2021-0" + i + "-01"));
            // add transaction to accounts
            user.addTransaction(revenueMicrosoft);

            // simulate monthly savings transactions
            Transaction savings = new Transaction("200.00", TransactionType.TRANSFER, accountAssetMain, accountAssetSavings, "Save money", "Savings", LocalDate.parse("2021-0" + i + "-23"));

            // add transaction to accounts
            user.addTransaction(savings);

            // instantiate new transactions
            Transaction expenseRent = new Transaction("300.0", TransactionType.WITHDRAWAL, accountAssetMain, accountLandlord, "Rent", "House", LocalDate.parse("2021-0" + i + "-03"));
            Transaction expenseEDP = new Transaction((df.format(Math.random() * 60 + 40)), TransactionType.WITHDRAWAL, accountAssetMain, accountEDP, "Eletricity and piped gas", "House", LocalDate.parse("2021-0" + i + "-03"));
            // add transaction rent to accounts
            user.addTransaction(expenseRent);
            // add transaction edp to accounts
            user.addTransaction(expenseEDP);

            for (int j = 10; j < 12; j++) {

                // instantiate new transactions
                Transaction expenseBar = new Transaction((df.format(Math.random() * 5 + 1)), TransactionType.WITHDRAWAL, accountAssetMain, accountBarFCTUC, "Bar", "Coffee", LocalDate.parse("2021-0" + i + "-" + (j + (int) Math.random() * 10 + 10)));
                // add transaction to accounts
                user.addTransaction(expenseBar);

                // instantiate new transactions
                Transaction expenseMcdonalds = new Transaction((df.format(Math.random() * 10 + 1)), TransactionType.WITHDRAWAL, accountAssetMain, accountMcdonalds, "McDonalds", "Food and drink", LocalDate.parse("2021-0" + i + "-" + (j + (int) Math.random() * 20 + 2)));
                // add transaction to accounts
                user.addTransaction(expenseMcdonalds);

                // instantiate new transactions
                Transaction expenseContinente = new Transaction((df.format(Math.random() * 30 + 10)), TransactionType.WITHDRAWAL, accountAssetMain, accountContinente, "Continente", "Groceries", LocalDate.parse("2021-0" + i + "-" + (j + (int) Math.random() * 20 + 2)));
                // add transaction to accounts
                user.addTransaction(expenseContinente);

            }
        }
    }

}
