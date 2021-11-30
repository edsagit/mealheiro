package model;

import java.util.*;

public class Account extends Observable implements Observer {

    private String id;
    private String userId;
    private AccountType accountType;
    private String name;
    private Boolean active;
    private String balance;
    private String iban;

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub

    }

}
