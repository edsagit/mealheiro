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
public class TransactionsController extends AbstractController {
    
    private Database db;
    private TransactionsView tv;

    public TransactionsController() {
    }
    
    public void setModel(Database db) {
        this.db = db;
    }

    public void setView(TransactionsView tv) {
        this.tv = tv;
        tv.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("")) {
//            System.out.println("LOGOUT BUTTON PRESSED");

        }
        super.actionPerformed(e);
    }
    
}
