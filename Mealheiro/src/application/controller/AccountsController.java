package application.controller;

import application.view.AccountsView;
import application.model.UserList;
import application.model.Account;
import java.awt.event.ActionEvent;

/**
 *
 * @author ed
 */
public class AccountsController extends AbstractController {

    private UserList db;
    private AccountsView av;

    public AccountsController(UserList model, AccountsView av) {
        this.db = model;
        this.av = av;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {
            System.out.println("Accounts controller: submit button clicked");
            if (db.getLoggedInUser() != null && av.getTfAccountsName() != null && av.getFtfAccountsBalance() != null) {
                Account acc = new Account(av.getTfAccountsName(), av.getFtfAccountsBalance(), av.getCbAccountsType(), av.getTfAccountsIban(), av.getTfAccountsBic(), av.getTfAccountsNumber());
                acc.setActive(true);
                db.getLoggedInUser().addAccount(acc);
                av.setLblAccountsInformation("Account created!");
            } else {
                av.setLblAccountsInformation("Account name and amount can't be empty!");
            }
        }
        super.actionPerformed(e);
    }

}
