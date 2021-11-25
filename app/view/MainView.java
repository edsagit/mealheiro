package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class MainView extends JPanel implements Observer {
    private JButton loginButton, registerButton;

    // private VendingMachine vm;

    public MainView() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add Buttons to Panel
        this.add(loginButton);
        this.add(registerButton);
    }

    // public void setModel(VendingMachine vm) {
    // this.vm = vm;
    // vm.addObserver(this);
    // }

    public void setController(EventListener el) {
        loginButton.addActionListener((ActionListener) el);
        registerButton.addActionListener((ActionListener) el);
    }

    public void update(Observable o, Object arg) {
    }
}
