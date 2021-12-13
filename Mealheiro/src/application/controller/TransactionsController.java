package application.controller;

import application.command.CommandHistory;
import application.command.addTransactionCommand;
import application.view.TransactionsView;
import application.model.UserList;
import application.model.Transaction;
import application.model.TransactionType;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

/**
 *
 * @author ed
 */
public class TransactionsController extends AbstractController {

    private CommandHistory ch;
    private UserList db;
    private TransactionsView tv;
    private Transaction originalTransaction;
    private Transaction inverseOriginal;

    public TransactionsController() {
    }

    public void setModel(UserList db) {
        this.db = db;
    }

    public void setView(TransactionsView tv) {
        this.tv = tv;
        tv.setController(this);
    }

    public void setCommandHistory(CommandHistory ch) {
        this.ch = ch;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {
            System.out.println("Transactions controller: submit button clicked");
            if (db.getLoggedInUser() != null) {
                if ((tv.getCbTransactionSourceAccount() != tv.getCbTransactionDestinationAccount())
                        && tv.getFtfTransactionAmount() != null) {
                    originalTransaction = new Transaction(tv.getFtfTransactionAmount(), tv.getCbTransactionType(),
                            tv.getCbTransactionSourceAccount(), tv.getCbTransactionDestinationAccount(),
                            tv.getTfTransactionDescription(), tv.getTfTransactionCategory(),
                            tv.getFtfTransactionDate());

                    inverseOriginal = new Transaction(tv.getFtfTransactionAmount(),
                            tv.getCbTransactionType(),
                            tv.getCbTransactionDestinationAccount(), tv.getCbTransactionSourceAccount(),
                            tv.getTfTransactionDescription(), tv.getTfTransactionCategory(),
                            tv.getFtfTransactionDate());

                    System.out.println("inv source: " + inverseOriginal.getSourceAccount());
                    System.out.println("inv dest: " + inverseOriginal.getDestinationAccount());
                    System.out.println("og source: " + originalTransaction.getSourceAccount());
                    System.out.println("og dest: " + originalTransaction.getDestinationAccount());
                    // add to command history undo stack
                    ch.addToUndoStack(new addTransactionCommand(db.getLoggedInUser(), inverseOriginal));
                    // execute transaction add transaction to respective source and destination
                    // accounts

                    // add transaction to user
                    db.getLoggedInUser().addTransaction(originalTransaction);
                    // set transactions information label
                    setUndoRedoButtons();
                    tv.setLblTransactionInformation("Transaction completed!");
                } else {
                    tv.setLblTransactionInformation("Amount can't be empty or accounts can't be the same.");
                }
            }
        }

        if (e.getActionCommand().equals("Undo")) {
            System.out.println("Transactions controller: undo button clicked");
            if (db.getLoggedInUser() != null) {
                // add transaction to command history redo stack
                ch.addToRedoStack(new addTransactionCommand(db.getLoggedInUser(), originalTransaction));
                // delete old transaction
                db.getLoggedInUser().getTransactions().remove(originalTransaction);
                // execute transactions
                ch.undo();
                setUndoRedoButtons();
                tv.setLblTransactionInformation("Transaction undone...");
            } else {
                tv.setLblTransactionInformation("Can't undo.");
            }
        }

        if (e.getActionCommand().equals("Redo")) {
            System.out.println("Transactions controller: redo button clicked");
            if (db.getLoggedInUser() != null) {
                // add transaction to command history undo stack
                ch.addToUndoStack(
                        new addTransactionCommand(db.getLoggedInUser(), inverseOriginal));
                // delete th
                db.getLoggedInUser().getTransactions().remove(inverseOriginal);
                ch.redo();
                setUndoRedoButtons();
                tv.setLblTransactionInformation("Transaction redone...");
            } else {
                tv.setLblTransactionInformation("Can't redo.");
            }
        }

        super.actionPerformed(e);
    }

    public void setUndoRedoButtons() {
        if (!ch.isUndoEmpty()) {
            tv.setUndoButtonEnabled(true);
        } else {
            tv.setUndoButtonEnabled(false);
        }
        if (!ch.isRedoEmpty()) {
            tv.setRedoButtonEnabled(true);
        } else {
            tv.setRedoButtonEnabled(false);
        }
    }

}
