package model;

import java.util.*;

public class UserModel extends Observable implements Observer {

    private String id;
    private String username;
    private String password;

    public UserModel(String username, String password) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        setChanged();
        notifyObservers();
    }

}
