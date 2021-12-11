package application.view;

import application.model.UserList;
import java.util.*;
import javax.swing.*;


public class MainView extends JPanel implements Observer {

    public JTabbedPane tp;
    private UserList db;

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
    
    /**
     * 
     * @param db UserList
     */
    public void setModel(UserList db) {
        this.db = db;
        db.addObserver(this);
    }
    
    /**
     * 
     * @param el EventListener
     */
    public void setController(EventListener el) {
    }

    /**
     * 
     * @param o Observable
     * @param arg Object
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Main view: updated");
    }

}
