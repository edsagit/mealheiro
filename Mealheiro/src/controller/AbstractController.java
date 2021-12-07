package controller;

import java.awt.event.*;
import javax.swing.event.*;

public abstract class AbstractController implements ActionListener, ChangeListener {

    private AbstractController parentController = null;

    public void setParentController(AbstractController ac) {
        this.parentController = ac;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.parentController != null) {
            ((ActionListener) this.parentController).actionPerformed(e);
        }
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        if (this.parentController != null) {
            ((ChangeListener) this.parentController).stateChanged(ce);
        }
    }
}
