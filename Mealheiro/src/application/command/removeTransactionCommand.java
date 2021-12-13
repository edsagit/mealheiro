/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package application.command;
//
//import application.model.Account;
//import application.model.Transaction;
//import application.model.User;
//
///**
// *
// * @author ed
// */
//public class removeTransactionCommand implements Command {
//    
//    private User user;
//    private Account source;
//    private Account destination;
//    private Transaction transaction;
//    
//    public removeTransactionCommand (User user, Account source, Account destination, Transaction transaction) {
//        this.user = user;
//        this.source = source;
//        this.destination = destination;
//        this.transaction = transaction;
//    }
//    
//    public void execute() {
//        user.removeTransaction(transaction);
//        source.removeTransaction(transaction);
//        destination.removeTransaction(transaction);
//    }
//    
//    public void undo() {
//        user.addTransaction(transaction);
//        source.addTransaction(transaction);
//        destination.addTransaction(transaction);
//    }
//    
//}
