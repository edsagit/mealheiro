package view;

import java.util.*;
import javax.swing.*;
import model.*;


public class MainView extends JPanel implements Observer {

    public JTabbedPane tp;

    private Database db;

    public MainView() {

        tp = new JTabbedPane();
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
    }

    public void setModel(Database db) {
        this.db = db;
        db.addObserver(this);
    }

    public void setController(EventListener el) {
//        if (this.db != null) {
//            this.update(this.db, null);
//        }
    }

    @Override
    public void update(Observable o, Object arg) {
//        this.tp.removeAll();
//        this.validate();
//        this.repaint();
    }

}
