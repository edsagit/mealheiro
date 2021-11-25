package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class MainView extends JPanel implements Observer {

    private JLabel lTitle;
    private JButton loginButton, registerButton;

    // private VendingMachine vm;

    public MainView() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Labels home
        lTitle = new JLabel("Mealheiro");
        lTitle.setBounds(25, 25, 100, 100);

        // Buttons home
        loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        registerButton = new JButton("Register");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to panel
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
