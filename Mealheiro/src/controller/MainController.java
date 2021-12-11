package controller;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import view.*;
import model.*;

public class MainController extends AbstractController {

    private UserList db;
    private User user;

    private MainView mv;

    private LoginView lv;
    private RegisterView rv;
    private DashboardView dv;
    private AccountsView av;
    private TransactionsView tv;

    private LoginController lc;
    private RegisterController rc;
    private DashboardController dc;
    private AccountsController ac;
    private TransactionsController tc;

    private JFrame mainWindow;

    public MainController() {

        // create Window
        mainWindow = new JFrame("Mealheiro");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(800, 600);
        mainWindow.setLocationRelativeTo(null);

        // create views
        mv = new MainView(); // MainView
        lv = new LoginView(); // LoginView
        rv = new RegisterView(); // RegisterView
        dv = new DashboardView(); // DashboardView
        av = new AccountsView(); // AccountsView
        tv = new TransactionsView();

        this.setView(mv);

        // create controllers
        lc = new LoginController();
        lc.setParentController(this);
        lc.setView(lv);
        // register controller
        rc = new RegisterController();
        rc.setView(rv);
        // dashboard controller
        dc = new DashboardController();
        dc.setParentController(this);
        dc.setView(dv);
        // accounts controller
        ac = new AccountsController();
        ac.setView(av);
        // transactions controller
        tc = new TransactionsController();
        tc.setView(tv);
        
        mainWindow.validate();
        mainWindow.setVisible(true);
    }

    public void displayView() {
        mainWindow.getContentPane().add(mv);
    }

    public void setModel(UserList db) {
        this.db = db;
        this.lv.setModel(db); // set LoginView model
        this.lc.setModel(db); // set LoginController model
        this.rv.setModel(db); // set RegisterView model
        this.rc.setModel(db); // set RegisterController model
        this.dv.setModel(db); // set DashboardView model
        this.dc.setModel(db); // set DashboardController model
        this.av.setModel(db);
        this.ac.setModel(db);
        this.tv.setModel(db);
        this.tc.setModel(db);
    }

    public void setView(MainView mv) {
        this.mv = mv;
        mv.tp.add(lv); // add LoginView panel to JTabbedPane
        mv.tp.add(rv); // add RegisterView panel to JTabbedPane
        mv.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component source = (Component) e.getSource();

        if (this.mv.isAncestorOf(source)) {

//            System.out.println(e.getActionCommand());
            if (e.getActionCommand().equals("Login")) {
              System.out.println("Main controller: login button clicked");
                if (db.loginUser(lv.getLoginUsername(), lv.getPfLoginPassword())) {
                    
                    db.setLoggedInUser(db.getUserByUsername(lv.getLoginUsername()));

                    mv.tp.removeAll(); // Remove all tabbed pane tabs
                    mainWindow.getContentPane().removeAll();

                    mv.tp.setTabPlacement(javax.swing.JTabbedPane.LEFT); // Set tab placement to left
                    mv.tp.add(dv); // Add DashboardView panel
                    mv.tp.add(av); // Add AccountsView panel
                    mv.tp.add(tv); // Add TransactionsView panel
                    
                    mainWindow.getContentPane().add(mv);
                    mainWindow.revalidate();
                    mainWindow.repaint();
                } else {
                    lv.setLoginInformation("Username or password are incorrect!");
                }
            }
            if (e.getActionCommand().equals("Logout")) {
                System.out.println("Controller: logout button clicked");
                db.setLoggedInUser(null);
                mv.tp.removeAll(); // Remove all tabbed pane tabs
                mv.tp.setTabPlacement(javax.swing.JTabbedPane.TOP); // Set tab placement to top
                mv.tp.add(lv); // Add LoginView panel
                mv.tp.add(rv); // Add RegisterView panel
                mainWindow.getContentPane().add(mv);
                mainWindow.revalidate();
                mainWindow.repaint();
            }
        }

        super.actionPerformed(e);
    }

}
