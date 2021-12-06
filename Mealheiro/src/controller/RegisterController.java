package controller;

import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import model.*;
import view.*;

/**
 *
 * @author ed
 */
public class RegisterController extends AbstractController {

    private Database db;
    private RegisterView rv;

    public RegisterController() {

    }

    public void setModel(Database db) {
        this.db = db;
    }

    public void setView(RegisterView rv) {
        this.rv = rv;
        rv.setController(this);
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        System.out.println("StateChanged " + ce);
//
//        for (Product p : this.vm.getProducts()) {
//            // System.out.println
//            p.setAvailableQuantity(this.rspv.getProductQuantity(p));
//        }
        super.stateChanged(ce);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Register")) {
            System.out.println("REGISTER BUTTON PRESSED");
            User tmpUser = new User(rv.getRegisterUsername(), rv.getRegisterEmail(), rv.getRegisterPassword());

            // register new user
            db.registerUser(tmpUser);
            
            // instantiate default asset account
            Account sourceOpeningBalance = new Account("Initial balance for " + rv.getTfRegisterBankName() + " account", rv.getFtfRegisterBalance(), AccountType.OPENING); // 
            Account destinationOpeningBalance = new Account(rv.getTfRegisterBankName(), "0", AccountType.ASSET); // destination account for default asset
//            tmpUser.addAccount(sourceOpeningBalance);
            destinationOpeningBalance.setActive(true);
            tmpUser.addAccount(destinationOpeningBalance); // add destination account to user
            Transaction openingBalance = new Transaction(rv.getFtfRegisterBalance(), TransactionType.OPENING_BALANCE, sourceOpeningBalance, destinationOpeningBalance, "Initial balance for " + rv.getTfRegisterBankName() + " account", "Opening balance");
            destinationOpeningBalance.addTransaction(openingBalance);
            tmpUser.addTransaction(openingBalance);
            
            // instantiate default asset savings account
            Account sourceOpeningSavingBalance = new Account("Initial balance for " + rv.getTfRegisterBankName() + " savings account", rv.getFtfRegisterSavingsBalance(), AccountType.OPENING);
            Account destinationOpeningSavingsBalance = new Account(rv.getTfRegisterBankName() + " savings account", "0", AccountType.ASSET);
//            tmpUser.addAccount(sourceOpeningSavingBalance); 
            destinationOpeningSavingsBalance.setActive(true);
            tmpUser.addAccount(destinationOpeningSavingsBalance); // add destination account to user
            Transaction savingsBalance = new Transaction(rv.getFtfRegisterSavingsBalance(), TransactionType.OPENING_BALANCE, sourceOpeningSavingBalance, destinationOpeningSavingsBalance, "Initial balance for " + rv.getTfRegisterBankName() + " savings account", "Opening balance");
            destinationOpeningSavingsBalance.addTransaction(savingsBalance);       
            tmpUser.addTransaction(savingsBalance);
            
            
            // set label text
            rv.setInformationLabelText("<html>" + "User <b>" + rv.getRegisterUsername() + "</b> registered successfully!" + "</html>");

            // clear text fields
            rv.setTfRegisterUsername("");
            rv.setTfRegisterEmail("");
            rv.setPfRegisterPassword("");
            rv.setTfRegisterBankName("");
            rv.setFtfRegisterBalance("");
            rv.setFtfRegisterSavingsBalance("");
            
//            tmpUser.getAccounts().forEach(a -> {
//                System.out.println(a.getId() + a.getName());
//            });
        }

        super.actionPerformed(e);
    }
}
