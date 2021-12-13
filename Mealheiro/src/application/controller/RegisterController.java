package application.controller;

import application.view.RegisterView;
import application.model.UserList;
import java.awt.event.ActionEvent;

/**
 *
 * @author ed
 */
public class RegisterController extends AbstractController {

    private UserList db;
    private RegisterView rv;

    public RegisterController() {

    }

    public void setModel(UserList db) {
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
            if (db.usernameExists(rv.getRegisterUsername())) {
                rv.setInformationLabelText("Username already exists.");
            } else {
                db.registerUser(rv.getRegisterUsername(), rv.getRegisterEmail(), rv.getRegisterPassword(),
                        rv.getTfRegisterBankName(), rv.getFtfRegisterBalance(), rv.getFtfRegisterSavingsBalance());
                rv.setInformationLabelText("User created successfully.");
                rv.update(db, null);
            }
        }

        super.actionPerformed(e);
    }
}
