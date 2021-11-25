import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import view.*;
import controller.*;

public class Main {
    public Main() {

        MainController mc = new MainController();
        mc.displayView();

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}