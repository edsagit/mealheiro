/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;

import model.*;
import view.*;

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
        if (e.getActionCommand().equals("")) {
//            System.out.println("LOGOUT BUTTON PRESSED");

        }
        super.actionPerformed(e);
    }

}
