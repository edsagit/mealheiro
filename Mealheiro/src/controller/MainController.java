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

//    private ProductSelectionView psv;
//    private ReStockProductsView rspv;
    private LoginController lc;
    private RegisterController rc;

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
//        Component source = (Component) e.getSource();
//        if (this.mv.isAncestorOf(source)) {
//            System.out.println("Acção feita sobre MainView");
//            System.out.println(e.getActionCommand());
//            if (e.getActionCommand().equals("Comprar")) {
//                mainWindow.getContentPane().removeAll();
//                mainWindow.getContentPane().add(psv);
//                mainWindow.revalidate();
//                mainWindow.repaint();
//            } else if (e.getActionCommand().equals("Abastecer")) {
//                mainWindow.getContentPane().removeAll();
//                mainWindow.getContentPane().add(rspv);
//                mainWindow.revalidate();
//                mainWindow.repaint();
//            }
//        }

//        super.actionPerformed(e);
    }

}
