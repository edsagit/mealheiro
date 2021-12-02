package model;

import java.util.*;

/**
 *
 * @author ed
 */
public class Database extends Observable implements Observer {

    private ArrayList<User> users;

    public Database() {
        this.users = new ArrayList<>();
    }

    public Boolean usernameExists(String username) {
        return users.stream().anyMatch(u -> (u.getUsername().equals(username))); 
    }

    public void registerUser(User u) {
        if (!usernameExists(u.getUsername())) {
            this.users.add(u);
            System.out.println(users.size());
            u.addObserver(this);
            setChanged();
            notifyObservers();
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public void update(Observable o, Object arg) {
        // Um produto foi modificado, notificar
        setChanged();
        notifyObservers();
    }
}
