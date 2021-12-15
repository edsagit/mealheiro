package application.controller;

import application.view.DashboardView;
import application.model.UserList;
import java.awt.event.ActionEvent;

/**
 *
 * @author ed
 */
public class DashboardController extends AbstractController {

    private UserList db;
    private DashboardView dv;

    public DashboardController(UserList model, DashboardView dv) {
        this.db = model;
        this.dv = dv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Logout")) {
            System.out.println("Controller: logout button clicked");
            db.logoutUser();
        }
        super.actionPerformed(e);
    }

}
