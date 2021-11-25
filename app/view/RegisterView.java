package view;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Constructor;
import java.util.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.border.*;

public class RegisterView extends JPanel implements Observer {

    private JPanel pRegister;
    private JLabel lRegisterTitle, lUsername, lPassword;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton bRegister, bBack;
    GridBagLayout gbl;
    GridBagConstraints gbc;

    public RegisterView() {

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // Layout register
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();

        // Panel register
        this.pRegister = new JPanel();
        this.pRegister.setLayout(gbl);
        this.pRegister.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Labels register
        lUsername = new JLabel("Username:");
        lPassword = new JLabel("Password:");
        lRegisterTitle = new JLabel("Register");

        // Textfield username
        tfUsername = new JTextField(10);

        // Passwordfield password
        pfPassword = new JPasswordField(10);

        // Buttons register
        bRegister = new JButton("Register");
        bBack = new JButton("Back");

        // Add components to main panel

        this.add(pRegister);
        this.add(lRegisterTitle);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.pRegister.add(lUsername, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 5;
        gbc.weightx = 1;
        this.pRegister.add(tfUsername, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        this.pRegister.add(lPassword, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 5;
        this.pRegister.add(pfPassword, gbc);

        this.add(bRegister);
        this.add(bBack);
    }

    public void setController(EventListener el) {
        bRegister.addActionListener((ActionListener) el);
        bBack.addActionListener((ActionListener) el);
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub

    }

}
