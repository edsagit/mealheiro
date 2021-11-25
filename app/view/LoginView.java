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

public class LoginView extends JPanel implements Observer {

    private JPanel pLogin;
    private JLabel lUsername, lPassword;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton bLogin, bBack;
    GridBagLayout gbl;
    GridBagConstraints gbc;

    public LoginView() {

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // Layout login
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();

        // Panel login
        this.pLogin = new JPanel();
        this.pLogin.setLayout(gbl);
        this.pLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Labels login
        lUsername = new JLabel("Username:");
        lPassword = new JLabel("Password:");

        // Textfield username
        tfUsername = new JTextField(10);
        // Passwordfield password
        pfPassword = new JPasswordField(10);

        // Buttons login
        bLogin = new JButton("Login");
        bBack = new JButton("Go back");

        // Add components to main panel
        this.add(pLogin);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.pLogin.add(lUsername, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 5;
        gbc.weightx = 1;
        this.pLogin.add(tfUsername, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        this.pLogin.add(lPassword, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 5;
        this.pLogin.add(pfPassword, gbc);

        this.add(bLogin);
        this.add(bBack);
    }

    public void setController(EventListener el) {
        bLogin.addActionListener((ActionListener) el);
        bBack.addActionListener((ActionListener) el);
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub

    }

}
