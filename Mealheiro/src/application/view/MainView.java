package application.view;

import application.controller.MainController;
import application.model.UserList;
import java.util.*;
import javax.swing.*;

public class MainView extends JPanel implements Observer {

    private JFrame mainWindow;
    private JTabbedPane tp;
    private UserList db;

    private LoginView lv;
    private RegisterView rv;
    private DashboardView dv;
    private AccountsView av;
    private TransactionsView tv;

    public MainView(UserList model) {
        // set the model
        this.db = model;
        // start controller
        MainController mc = new MainController(model, this);
        // create Window
        mainWindow = new JFrame("Mealheiro");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(800, 600);
        mainWindow.setLocationRelativeTo(null);

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

        mainWindow.add(this);
        this.db.addObserver(this);

        lv = new LoginView(model); // LoginView
        rv = new RegisterView(model); // RegisterView
        dv = new DashboardView(model); // DashboardView
        av = new AccountsView(model); // AccountsView
        tv = new TransactionsView(model); // TransactionsView

        tp.add(lv); // add LoginView panel to JTabbedPane
        tp.add(rv); // add RegisterView panel to JTabbedPane

        mainWindow.validate();
        mainWindow.setVisible(true);
    }
//
//    /**
//     *
//     * @param db UserList
//     */
//    public void setModel(UserList db) {
//        this.db = db;
//        db.addObserver(this);
//    }

//    /**
//     *
//     * @param el EventListener
//     */
//    public void setController(EventListener el) {
//    }
    /**
     *
     * @param o Observable
     * @param arg Object
     */
    @Override
    public void update(Observable o, Object arg) {
        
        if (arg != null) {
            System.out.println("Main view: updated");
            if (db.getLoggedInUser() != null && arg.equals("session_setted")) {
                tp.removeAll();
                tp.add(dv);
                tp.add(av);
                tp.add(tv);
                tp.setTabPlacement(javax.swing.JTabbedPane.LEFT);
                mainWindow.revalidate();
                mainWindow.repaint();
            } else if (arg.equals("session_unsetted")) {
                tp.removeAll();
                lv.setLoginInformation("");
                tp.add(lv);
                tp.add(rv);
                tp.setTabPlacement(javax.swing.JTabbedPane.TOP);
                mainWindow.revalidate();
                mainWindow.repaint();
            }
        }
    }

}
