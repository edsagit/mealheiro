package controller;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

import view.*;

public class MainController extends AbstractController {

    private MainView mv;
    private LoginView lv;
    private RegisterView rv;

    private LoginController lc;
    private RegisterController rc;

    private JFrame mainWindow;

    public MainController() {

        // create Window
        mainWindow = new JFrame("Mealheiro");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(400, 400);
        mainWindow.setLocation(100, 100);

        // create views
        mv = new MainView(); // Main view
        lv = new LoginView(); // Login view
        rv = new RegisterView(); // Register view

        this.setView(mv);

        // create controllers
        lc = new LoginController();
        lc.setParentController(this);
        lc.setView(lv);

        rc = new RegisterController();
        rc.setParentController(this);
        rc.setView(rv);

        mainWindow.validate();
        mainWindow.setVisible(true);
    }

    public void displayView() {
        mainWindow.getContentPane().add(mv);
    }

    // public void setModel(VendingMachine vm) {
    // this.vm = vm;
    // this.psv.setModel(vm);
    // this.rspv.setModel(vm);
    // this.psc.setModel(vm);
    // this.rspc.setModel(vm);
    // }

    public void setView(MainView mv) {
        this.mv = mv;
        mv.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component source = (Component) e.getSource();
        if (this.mv.isAncestorOf(source)) {
            System.out.println("Acção feita sobre MainView");
            System.out.println(e.getActionCommand());
            if (e.getActionCommand().equals("Login")) {
                mainWindow.getContentPane().removeAll();
                mainWindow.getContentPane().add(lv);
                mainWindow.revalidate();
                mainWindow.repaint();
            } else if (e.getActionCommand().equals("Register")) {
                mainWindow.getContentPane().removeAll();
                mainWindow.getContentPane().add(rv);
                mainWindow.revalidate();
                mainWindow.repaint();
            }
        } else if (this.lv.isAncestorOf(source)) {
            System.out.println("Action made on LoginView");
            System.out.println(e.getActionCommand());
            if (e.getActionCommand().equals("Back")) {
                mainWindow.getContentPane().removeAll();
                mainWindow.getContentPane().add(mv);
                mainWindow.revalidate();
                mainWindow.repaint();
            }
        } else if (this.rv.isAncestorOf(source)) {
            System.out.println("Action made on RegisterView");
            System.out.println(e.getActionCommand());
            if (e.getActionCommand().equals("Back")) {
                mainWindow.getContentPane().removeAll();
                mainWindow.getContentPane().add(mv);
                mainWindow.revalidate();
                mainWindow.repaint();
            }
        }

        super.actionPerformed(e);
    }

}
