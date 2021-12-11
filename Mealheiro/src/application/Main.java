package application;

import application.controller.MainController;
import application.model.UserList;

/**
 *
 * @author ed
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main main = new Main();
    }

    private UserList db;

    public Main() {

        db = new UserList();

        db.instantiateDummyData();

        MainController mc = new MainController();
        mc.setModel(db);
        mc.displayView();
    }

}
