/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    public AccountsController() {
    }

    public void setModel(UserList db) {
        this.db = db;
    }

    public void setView(AccountsView av) {
        this.av = av;
        av.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {
            System.out.println("Accounts controller: submit button clicked");
            if (db.getLoggedInUser() != null) {
                Account acc = new Account(av.getTfAccountsName(), av.getFtfAccountsBalance(), av.getCbAccountsType(), av.getTfAccountsIban(), av.getTfAccountsBic(), av.getTfAccountsNumber());
                db.getLoggedInUser().addAccount(acc);
                System.out.println("asd");
                av.update(db, null);
            }
        }
        super.actionPerformed(e);
    }

}
