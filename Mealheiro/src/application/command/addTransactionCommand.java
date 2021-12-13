/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.command;

import application.model.Account;
import application.model.Transaction;
import application.model.User;

/**
 *
 * @author ed
 */
public class addTransactionCommand implements Command {

    private User user;
    private Account source;
    private Account destination;
    private Transaction transaction;

    public addTransactionCommand(User user, Transaction transaction) {
        this.user = user;
//        this.source = source;
//        this.destination = destination;
        this.transaction = transaction;
    }

    public void execute() {
        user.addTransaction(transaction);
    }

    public void undo() {
        user.removeTransaction(transaction);
    }

}
