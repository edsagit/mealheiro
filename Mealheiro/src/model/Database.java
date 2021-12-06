package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 *
 * @author ed
 */
public class Database extends Observable implements Observer {

    private ArrayList<User> users;
    private User loggedInUser = null;

    public Database() throws IOException {
//        List<String> result = Files.readAllLines(Paths.get("users.txt"));
//        System.out.println(result);
//        for (String s : result) {
//            String[] parts = s.split(",");
//            User tmpUser = new User();
//            System.out.println(parts[0]);
//        }
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

            try {
                FileWriter fw = new FileWriter("users.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(u.toString() + "\n");
                bw.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

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
        setChanged();
        notifyObservers();
    }

    @Override
    public void update(Observable o, Object arg) {
        // Um produto foi modificado, notificar
        setChanged();
        notifyObservers();
    }
}
