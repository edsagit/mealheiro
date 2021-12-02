package controller;

import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import model.*;
import view.*;

/**
 *
 * @author ed
 */
public class RegisterController extends AbstractController {

    private Database db;
    private RegisterView rv;
    private String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

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
    public void stateChanged(ChangeEvent ce) {
        System.out.println("StateChanged " + ce);
//
//        for (Product p : this.vm.getProducts()) {
//            // System.out.println
//            p.setAvailableQuantity(this.rspv.getProductQuantity(p));
//        }
        super.stateChanged(ce);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Register")) {
            System.out.println("REGISTER BUTTON PRESSED");
            // not null validation
            if (rv.getRegisterUsername() != null && rv.getRegisterEmail() != null && rv.getRegisterPassword() != null) {
                // register new user
                db.registerUser(new User(rv.getRegisterUsername(), rv.getRegisterEmail(), rv.getRegisterPassword()));
//                    System.out.println(db.usernameExists(rv.getRegisterUsername()));
            }
        }
        super.actionPerformed(e);
    }
}
