package controller;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

import view.*;

public class LoginController extends AbstractController {

    private LoginView lv;

    public void setView(LoginView lv) {
        this.lv = lv;
        lv.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component source = (Component) e.getSource();
        if (this.lv.isAncestorOf(source)) {
            System.out.println("Acção feita sobre LoginView");
            System.out.println(e.getActionCommand());
            if (e.getActionCommand().equals("Login")) {
                System.out.println("Login button pressed on LoginView");
                lv.revalidate();
                lv.repaint();
            } else if (e.getActionCommand().equals("Register")) {
                // lv.getContentPane().removeAll();
                // lv.getContentPane().add(rv);
                lv.revalidate();
                lv.repaint();
            }
        }

        super.actionPerformed(e);
    }

}
