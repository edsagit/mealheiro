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
            User tmpUser = new User(rv.getRegisterUsername(), rv.getRegisterEmail(), rv.getRegisterPassword());

            // register new user
            db.registerUser(tmpUser);
            tmpUser.addAccount(new Account(rv.getTfRegisterBankName(), rv.getFtfRegisterBalance()));
            tmpUser.addAccount(new Account(rv.getTfRegisterBankName() + "savings account", rv.getFtfRegisterSavingsBalance()));
            tmpUser.addAccount(new Account("Cash wallet", "0"));
            // set label text
            rv.setInformationLabelText("<html>" + "User <b>" + rv.getRegisterUsername() + "</b> registered successfully!" + "</html>");
//                    System.out.println(db.usernameExists(rv.getRegisterUsername()));

            // clear text fields
            rv.setTfRegisterUsername("");
            rv.setTfRegisterEmail("");
            rv.setPfRegisterPassword("");
            rv.setTfRegisterBankName("");
            rv.setFtfRegisterBalance("");
            rv.setFtfRegisterSavingsBalance("");
            
            tmpUser.getAccounts().forEach(a -> {
                System.out.println(a.getId() + a.getName());
            });
        }

        super.actionPerformed(e);
    }
}
