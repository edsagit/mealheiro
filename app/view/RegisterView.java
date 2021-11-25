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

    public void setController(EventListener el) {
        bLogin.addActionListener((ActionListener) el);
        bBack.addActionListener((ActionListener) el);
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub

    }

}
