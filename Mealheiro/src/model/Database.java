package model;

import java.util.*;

/**
 *
 * @author ed
 */
public class Database extends Observable implements Observer {

    private ArrayList<User> users;
    private User loggedInUser = null; 

    public Database() {
        this.users = new ArrayList<>();
    }

    public Boolean usernameExists(String username) {
        return users.stream().anyMatch(u -> (u.getUsername().equals(username)));
    }

    public User getUserByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public Boolean loginUser(String username, String password) {
        return usernameExists(username) && getUserByUsername(username).getPassword().equals(password);
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
    
    public User getLoggedInUser() {
        return this.loggedInUser;
    }
    
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    @Override
    public void update(Observable o, Object arg) {
        // Um produto foi modificado, notificar
        setChanged();
        notifyObservers();
    }
}
