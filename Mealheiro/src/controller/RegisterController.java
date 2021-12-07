package controller;

import java.awt.event.ActionEvent;

import model.*;
import view.*;

/**
 *
 * @author ed
 */
public class RegisterController extends AbstractController {

    private Database db;
    private RegisterView rv;

    public RegisterController() {

    }

    public void setModel(Database db) {
        this.db = db;
    }

    public void setView(RegisterView rv) {
        this.rv = rv;
        rv.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Register")) {
            System.out.println("Controller: register button clicked");

            // register new user
            db.registerUser(rv.getRegisterUsername(), rv.getRegisterEmail(), rv.getRegisterPassword(), rv.getTfRegisterBankName(), rv.getFtfRegisterBalance(), rv.getFtfRegisterSavingsBalance());
            rv.update(db, null);
        }

        super.actionPerformed(e);
    }
}
