/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import view.*;
import model.*;

/**
 *
 * @author ed
 */
public class DashboardController extends AbstractController {
    
    private Database db;
    private DashboardView dv;

    public DashboardController() {
    }
    
    public void setModel(Database db) {
        this.db = db;
    }

    public void setView(DashboardView dv) {
        this.dv = dv;
        dv.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Logout")) {
            System.out.println("LOGOUT BUTTON PRESSED");

            
        }
        super.actionPerformed(e);
    }
    
}
