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

    public RegisterController(UserList model, RegisterView rv) {
        this.db = model;
        this.rv = rv;
    }

//    public void setModel(UserList db) {
//        this.db = db;
//    }

//    public void setView(RegisterView rv) {
//        this.rv = rv;
//        rv.setController(this);
//    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Register")) {
            System.out.println("Controller: register button clicked");

            // register new user
            if (db.usernameExists(rv.getRegisterUsername())) {
//                System.out.println("ASD1");
                rv.setInformationLabelText("Username already exists.");
                rv.update(db, 0);
            } else {
//                System.out.println("ASD2");
                db.registerUser(rv.getRegisterUsername(), rv.getRegisterEmail(), rv.getRegisterPassword(),
                        rv.getTfRegisterBankName(), rv.getFtfRegisterBalance(), rv.getFtfRegisterSavingsBalance());
                ;
                rv.update(db, 1);
            }
        }

        super.actionPerformed(e);
    }
}
