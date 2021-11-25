package controller;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

import view.*;

public class RegisterController extends AbstractController {

    private RegisterView rv;

    public void setView(RegisterView rv) {
        this.rv = rv;
        rv.setController(this);
    }

}
