package controller;

import java.awt.event.ActionEvent;
import model.Product;
import model.VendingMachine;
import view.*;
import model.*;

/**
 *
 * @author ed
 */
public class LoginController extends AbstractController {

    private Database db;
    private LoginView lv;

    public LoginController() {

    }

    public void setModel(Database db) {
        this.db = db;
    }

    public void setView(LoginView lv) {
        this.lv = lv;
        lv.setController(this);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            System.out.println("LOGIN BUTTON PRESSED");

        }
        super.actionPerformed(e);
    }
}
