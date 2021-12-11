package controller;

import java.awt.event.ActionEvent;
import java.time.LocalDate;

import model.*;
import view.*;

/**
 *
 * @author ed
 */
public class TransactionsController extends AbstractController {
    
    private UserList db;
    private TransactionsView tv;

    public TransactionsController() {
    }
    
    public void setModel(UserList db) {
        this.db = db;
    }

    public void setView(TransactionsView tv) {
        this.tv = tv;
        tv.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {
            System.out.println("Transactions controller: submit button clicked");
            if (db.getLoggedInUser() != null && (tv.getCbTransactionSourceAccount() != tv.getCbTransactionDestinationAccount())) {
                Transaction t = new Transaction(tv.getFtfTransactionAmount(), tv.getCbTransactionType(), tv.getCbTransactionSourceAccount(), tv.getCbTransactionDestinationAccount(), tv.getTfTransactionDescription(), tv.getTfTransactionCategory(), tv.getFtfTransactionDate());
                tv.getCbTransactionSourceAccount().addTransaction(t);
                tv.getCbTransactionDestinationAccount().addTransaction(t);
                db.getLoggedInUser().addTransaction(t);
                tv.setLblTransactionInformation("Transaction completed!");
            } else {
                tv.setLblTransactionInformation("Accounts can't be the same.");
            }
            tv.update(db, null);
        }
        super.actionPerformed(e);
    }
    
}
