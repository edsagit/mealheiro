package application.controller;

import application.view.LoginView;
import application.model.UserList;
import java.awt.event.ActionEvent;

/**
 *
 * @author ed
 */
public class LoginController extends AbstractController {

    private UserList db;
    private LoginView lv;

    public LoginController() {

    }

    public void setModel(UserList db) {
        this.db = db;
    }

    public void setView(LoginView lv) {
        this.lv = lv;
        lv.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getActionCommand().equals("Login")) {
//            System.out.println("Login controller: login button clicked");
//            if (db.loginUser(lv.getLoginUsername(), lv.getPfLoginPassword())) {
//                db.setLoggedInUser(db.getUserByUsername(lv.getLoginUsername()));
//            } else {
//                db.setLoggedInUser(null);
//            }
//        }
        super.actionPerformed(e);
    }
}
