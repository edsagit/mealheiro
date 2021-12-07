package application;

import controller.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import model.*;

/**
 *
 * @author ed
 */
public class Main {

    private Database db;

    public Main() {
        // simulate data, could be loaded from database

        db = new Database();
        MainController mc = new MainController();
        mc.setModel(db);
        mc.displayView();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, fall back to cross-platform
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                // Not worth my time
            }
        }
        new Main();
    }

}
