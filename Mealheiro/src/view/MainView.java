package view;

import controller.*;
import model.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

public class MainView extends JPanel implements Observer {

    public JTabbedPane tp;

    private JButton logoutButton;

    private Database db;

    public MainView() {

//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        tp = new JTabbedPane();
//        tp.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tp, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tp, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                .addContainerGap())
        );

        // Logout button
        logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.LEFT_ALIGNMENT);

//        // Botao para fechar
//        abastecerButton = new JButton("Abastecer");
//        abastecerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // adicionar tudo ao painel principal
//        this.add(logoutButton);
//        this.add(comprarButton);
//
//        this.add(abastecerButton);
    }

    public void setModel(Database db) {
        this.db = db;
        db.addObserver(this);
    }

    public void setController(EventListener el) {
//        abastecerButton.addActionListener((ActionListener) el);
//        comprarButton.addActionListener((ActionListener) el);
    }

    public void update(Observable o, Object arg) {
    }

}
