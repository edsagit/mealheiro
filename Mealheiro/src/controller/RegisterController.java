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
            System.out.println("REGISTER BUTTON PRESSED");
            if (rv.getRegisterUsername() != null && rv.getRegisterEmail() != null && rv.getRegisterPassword() != null) {
                db.registerUser(new User(rv.getRegisterUsername(), rv.getRegisterEmail(), rv.getRegisterPassword()));
                System.out.println(db.usernameExists(rv.getRegisterUsername()));
            }
        }
        super.actionPerformed(e);
    }
}
