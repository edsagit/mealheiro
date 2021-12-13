package application.command;

import application.model.Transaction;
import application.model.User;

/**
 *
 * @author ed
 */
public class addTransactionCommand implements Command {

    private User user;
    private Transaction transaction;

    public addTransactionCommand(User user, Transaction transaction) {
        this.user = user;
        this.transaction = transaction;
    }

    public void execute() {
        user.addTransaction(transaction);
    }

    public void undo() {
        user.removeTransaction(transaction);
    }

}
