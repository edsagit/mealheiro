package application;

import application.controller.MainController;
import application.model.UserList;
import application.view.MainView;

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

    private UserList model;
    
    /**
     * Main constructor
     */
    public Main() {

        model = new UserList();
        
        // populate UserList with some dummy data
        model.instantiateDummyData();
        
        MainView mv = new MainView(model);
//        MainController mc = new MainController();
//        mc.setModel(model);
//        mc.displayView();
    }

}
