package controller;

import view.*;
import model.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class MainController extends AbstractController {

//    private VendingMachine vm;
    private Database db;

    private MainView mv;

    private LoginView lv;
    private RegisterView rv;
    private DashboardView dv;
    private AccountsView av;

//    private ProductSelectionView psv;
//    private ReStockProductsView rspv;
    private LoginController lc;
    private RegisterController rc;
    private DashboardController dc;

//    private ProductSelectionController psc;
//    private ReStockProductsController rspc;
    private JFrame mainWindow;

    public MainController() {

        // create Window
        mainWindow = new JFrame("Mealheiro");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(800, 600);
        mainWindow.setLocationRelativeTo(null);

        // create views
        mv = new MainView();
        lv = new LoginView();
        rv = new RegisterView();

//        psv = new ProductSelectionView();
//        rspv = new ReStockProductsView();
        this.setView(mv);

        // create controllers
        lc = new LoginController();
        lc.setParentController(this);
        lc.setView(lv);

        rc = new RegisterController();
        rc.setView(rv);

//        psc = new ProductSelectionController();
//        psc.setParentController(this);
//        psc.setView(psv);
//
//        rspc = new ReStockProductsController();
//        rspc.setParentController(this);
//        rspc.setView(rspv);
        mainWindow.validate();
        mainWindow.setVisible(true);
    }

    public void displayView() {
        mainWindow.getContentPane().add(mv);
    }

    public void setModel(Database db) {
        this.db = db;
        this.lv.setModel(db); // set LoginView model
        this.lc.setModel(db); // set LoginController model
        this.rv.setModel(db); // set RegisterView model
        this.rc.setModel(db); // set RegisterController model
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
            System.out.println("Acção feita sobre MainView");
            System.out.println(e.getActionCommand());
            if (e.getActionCommand().equals("Login")) {
                mv.tp.removeAll(); // Remove all tabbed pane tabs
                mainWindow.getContentPane().removeAll();
                dv = new DashboardView(); // Instantiate DashboardView
                dc = new DashboardController();
                dc.setParentController(this);
                dc.setView(dv);
                av = new AccountsView(); // Instantiate AccountsView
                mv.tp.setTabPlacement(javax.swing.JTabbedPane.LEFT); // Set tab placement to left
                mv.tp.add(dv); // Add DashboardView panel
                mv.tp.add(av); // Add AccountsView panel
                mainWindow.getContentPane().add(mv);
                mainWindow.revalidate();
                mainWindow.repaint();
            }
            if (e.getActionCommand().equals("Logout")) {
                mv.tp.removeAll(); // Remove all tabbed pane tabs
                mainWindow.getContentPane().removeAll();  
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
