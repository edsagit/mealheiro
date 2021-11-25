package controller;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

import view.*;

public class LoginController extends AbstractController {

    private LoginView lv;

    public void setView(LoginView lv) {
        this.lv = lv;
        lv.setController(this);
    }

}
