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

    public LoginController(UserList model, LoginView lv) {
        this.db = model;
        this.lv = lv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            System.out.println("Login controller: login button clicked");
            if (db.loginUser(lv.getLoginUsername(), lv.getPfLoginPassword())) {
                db.setLoggedInUser(db.getUserByUsername(lv.getLoginUsername()));
//                lv.update(null, 1);
            } else {
                lv.update(null, 0);
            }
        }
        super.actionPerformed(e);
    }
}
