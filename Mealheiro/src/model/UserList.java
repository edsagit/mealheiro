package model;

import java.util.*;

/**
 *
 * @author ed
 */
public class UserList extends Observable {

    private ArrayList<User> users;
    private User loggedInUser = null;

    public UserList() {
        this.users = new ArrayList<>();
    }

    public Boolean usernameExists(String username) {
        // for (User u : users) {
        // System.out.println(u.getUsername());
        // }
        // System.out.println(username);
        return users.stream().anyMatch(u -> (u.getUsername().equals(username)));
    }

    public User getUserByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public Boolean loginUser(String username, String password) {
        return usernameExists(username) && getUserByUsername(username).getPassword().equals(password);
    }

    public void registerUser(String username, String email, String password, String bankName, String defaultBalance,
            String savingsBalance) {
        if (!usernameExists(username)) {
            System.out.println("UserList Model: updated");

            User tmpUser = new User(username, email, password);

            // instantiate default asset account
            Account sourceOpeningBalance = new Account("Initial balance for " + bankName + " account", defaultBalance,
                    AccountType.OPENING); //
            Account destinationOpeningBalance = new Account(bankName, "0", AccountType.ASSET); // destination account
            // for default asset
            destinationOpeningBalance.setActive(true); // set account active
            tmpUser.addAccount(destinationOpeningBalance); // add destination account to user
            Transaction transactionOpeningBalance = new Transaction(defaultBalance, TransactionType.OPENING_BALANCE,
                    sourceOpeningBalance, destinationOpeningBalance, "Initial balance for " + bankName + " account",
                    "Opening balance");
            destinationOpeningBalance.addTransaction(transactionOpeningBalance);
            tmpUser.addTransaction(transactionOpeningBalance);

            // instantiate default asset savings account
            Account sourceOpeningSavingBalance = new Account("Initial balance for " + bankName + " savings account",
                    savingsBalance, AccountType.OPENING);
            Account destinationOpeningSavingsBalance = new Account(bankName + " savings account", "0",
                    AccountType.ASSET);
            destinationOpeningSavingsBalance.setActive(true); // set account active
            tmpUser.addAccount(destinationOpeningSavingsBalance); // add destination account to user
            Transaction transactionSavingsBalance = new Transaction(savingsBalance, TransactionType.OPENING_BALANCE,
                    sourceOpeningSavingBalance, destinationOpeningSavingsBalance,
                    "Initial balance for " + bankName + " savings account", "Opening balance");
            destinationOpeningSavingsBalance.addTransaction(transactionSavingsBalance);
            tmpUser.addTransaction(transactionSavingsBalance);

            this.users.add(tmpUser);
            // user.addObserver(this);
            setChanged();
            notifyObservers();
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getLoggedInUser() {
        if (this.loggedInUser != null) {
            return this.loggedInUser;
        } else {
            return null;
        }
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
        setChanged();
        notifyObservers();
    }

    // @Override
    // public void update(Observable o, Object arg) {
    // setChanged();
    // notifyObservers();
    // }
}
