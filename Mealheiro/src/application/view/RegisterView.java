/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package application.view;

import application.model.UserList;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;


/**
 *
 * @author ed
 */
public class RegisterView extends JPanel implements Observer {

    private UserList db;
    private Boolean userValid = false;
    private Boolean emailValid = false;
    private Boolean passwordValid = false;
    private Boolean bankNameValid = false;
    private Boolean balanceValid = false;
    private Boolean savingsValid = false;

    /**
     * Creates new form RegisterView
     */
    public RegisterView() {
        initComponents();
        bRegister.setEnabled(false);
        tfRegisterUsername.setDocument(new JTextFieldLimit(10)); // Limit character textfield input
        tfRegisterUsername.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onChange(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onChange(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onChange(e);
            }

            private void onChange(DocumentEvent e) {
                if (!tfRegisterUsername.getText().isEmpty() && !tfRegisterUsername.getText().isBlank()) {
                    setUserValid(true);
                } else {
                    setUserValid(false);
                }
                if (getUserValid() && getEmailValid() && getPasswordValid() && getBankNameValid() && getBalanceValid()
                        && getSavingsValid()) {
                    bRegister.setEnabled(true);
                } else {
                    bRegister.setEnabled(false);
                }
            }
        });
        tfRegisterEmail.setDocument(new JTextFieldLimit(30));
        tfRegisterEmail.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onChange(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onChange(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onChange(e);
            }

            private void onChange(DocumentEvent e) {
                String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                if (tfRegisterEmail.getText().matches(EMAIL_PATTERN)) {
                    setEmailValid(true);
                } else {
                    setEmailValid(false);
                }
                if (getUserValid() && getEmailValid() && getPasswordValid() && getBankNameValid() && getBalanceValid()
                        && getSavingsValid()) {
                    bRegister.setEnabled(true);
                } else {
                    bRegister.setEnabled(false);
                }
            }
        });
        pfRegisterPassword.setDocument(new JTextFieldLimit(15));
        pfRegisterPassword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onChange(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onChange(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onChange(e);
            }

            private void onChange(DocumentEvent e) {
                if (!pfRegisterPassword.getText().isEmpty() && !pfRegisterPassword.getText().isBlank()) {
                    setPasswordValid(true);
                } else {
                    setPasswordValid(false);
                }
                if (getUserValid() && getEmailValid() && getPasswordValid() && getBankNameValid() && getBalanceValid()
                        && getSavingsValid()) {
                    bRegister.setEnabled(true);
                } else {
                    bRegister.setEnabled(false);
                }
            }
        });
        tfRegisterBankName.setDocument(new JTextFieldLimit(15));
        tfRegisterBankName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onChange(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onChange(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onChange(e);
            }

            private void onChange(DocumentEvent e) {
                if (!tfRegisterBankName.getText().isEmpty() && !tfRegisterBankName.getText().isBlank()) {
                    setBankNameValid(true);
                } else {
                    setBankNameValid(false);
                }
                if (getUserValid() && getEmailValid() && getPasswordValid() && getBankNameValid() && getBalanceValid()
                        && getSavingsValid()) {
                    bRegister.setEnabled(true);
                } else {
                    bRegister.setEnabled(false);
                }
            }
        });
        ftfRegisterBalance.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onChange(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onChange(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onChange(e);
            }

            private void onChange(DocumentEvent e) {
                if (!ftfRegisterBalance.getText().isEmpty() && !ftfRegisterBalance.getText().isBlank()) {
                    setBalanceValid(true);
                } else {
                    setBalanceValid(false);
                }
                if (getUserValid() && getEmailValid() && getPasswordValid() && getBankNameValid() && getBalanceValid()
                        && getSavingsValid()) {
                    bRegister.setEnabled(true);
                } else {
                    bRegister.setEnabled(false);
                }
            }
        });
        ftfRegisterSavingsBalance.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onChange(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onChange(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onChange(e);
            }

            private void onChange(DocumentEvent e) {
                // verify if registerSavingsBalance isn't empty or blank
                if (!ftfRegisterSavingsBalance.getText().isEmpty() && !ftfRegisterSavingsBalance.getText().isBlank()) {
                    setSavingsValid(true);
                } else {
                    setSavingsValid(false);
                }

                // verify if the credentials are valid, if so enable the register button
                if (getUserValid() && getEmailValid() && getPasswordValid() && getBankNameValid() && getBalanceValid()
                        && getSavingsValid()) {
                    bRegister.setEnabled(true);
                } else {
                    bRegister.setEnabled(false);
                }
            }
        });
    }

    public Boolean getUserValid() {
        return userValid;
    }

    public void setUserValid(Boolean userValid) {
        this.userValid = userValid;
    }

    public Boolean getEmailValid() {
        return emailValid;
    }

    public void setEmailValid(Boolean emailValid) {
        this.emailValid = emailValid;
    }

    public Boolean getPasswordValid() {
        return passwordValid;
    }

    public void setPasswordValid(Boolean passwordValid) {
        this.passwordValid = passwordValid;
    }

    public Boolean getBankNameValid() {
        return bankNameValid;
    }

    public void setBankNameValid(Boolean bankNameValid) {
        this.bankNameValid = bankNameValid;
    }

    public Boolean getBalanceValid() {
        return balanceValid;
    }

    public void setBalanceValid(Boolean balanceValid) {
        this.balanceValid = balanceValid;
    }

    public Boolean getSavingsValid() {
        return savingsValid;
    }

    public void setSavingsValid(Boolean savingsValid) {
        this.savingsValid = savingsValid;
    }

    public void setModel(UserList db) {
        this.db = db;
        db.addObserver(this);
    }

    public void setInformationLabelText(String text) {
        lblRegisterInformation.setText(text);
    }

    public void setController(EventListener el) {
        bRegister.addActionListener((ActionListener) el);
    }

    public void setFtfRegisterBalance(String ftfRegisterBalance) {
        this.ftfRegisterBalance.setText(ftfRegisterBalance);
    }

    public void setFtfRegisterSavingsBalance(String ftfRegisterSavingsBalance) {
        this.ftfRegisterSavingsBalance.setText(ftfRegisterSavingsBalance);
    }

    public void setPfRegisterPassword(String pfRegisterPassword) {
        this.pfRegisterPassword.setText(pfRegisterPassword);
    }

    public void setTfRegisterBankName(String tfRegisterBankName) {
        this.tfRegisterBankName.setText(tfRegisterBankName);
    }

    public void setTfRegisterEmail(String tfRegisterEmail) {
        this.tfRegisterEmail.setText(tfRegisterEmail);
    }

    public void setTfRegisterUsername(String tfRegisterUsername) {
        this.tfRegisterUsername.setText(tfRegisterUsername);
    }

    public String getFtfRegisterBalance() {
        return ftfRegisterBalance.getText();
    }

    public String getFtfRegisterSavingsBalance() {
        return ftfRegisterSavingsBalance.getText();
    }

    public String getTfRegisterBankName() {
        return tfRegisterBankName.getText();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Register view: updated");
        clearTextFields();
    }

    public String getRegisterUsername() {
        return tfRegisterUsername.getText();
    }

    public String getRegisterEmail() {
        return tfRegisterEmail.getText();
    }

    public String getRegisterPassword() {
        return pfRegisterPassword.getText();
    }

    public void clearTextFields() {
        tfRegisterUsername.setText("");
        tfRegisterEmail.setText("");
        pfRegisterPassword.setText("");
        tfRegisterBankName.setText("");
        ftfRegisterBalance.setValue(null);
        ftfRegisterSavingsBalance.setValue(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblRegisterUsername = new javax.swing.JLabel();
        tfRegisterUsername = new javax.swing.JTextField();
        lblRegisterEmail = new javax.swing.JLabel();
        tfRegisterEmail = new javax.swing.JTextField();
        lblRegisterPassword = new javax.swing.JLabel();
        pfRegisterPassword = new javax.swing.JPasswordField();
        bRegister = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lblBankName = new javax.swing.JLabel();
        tfRegisterBankName = new javax.swing.JTextField();
        lblBalance = new javax.swing.JLabel();
        ftfRegisterBalance = new javax.swing.JFormattedTextField();
        lblSavingsBalance = new javax.swing.JLabel();
        ftfRegisterSavingsBalance = new javax.swing.JFormattedTextField();
        lblRegisterBalanceEuro = new javax.swing.JLabel();
        lblRegisterSavingsEuro = new javax.swing.JLabel();
        lblRegisterInformation = new javax.swing.JLabel();

        setName("Register"); // NOI18N

        lblRegisterUsername.setText("Username");

        tfRegisterUsername.setColumns(5);
        tfRegisterUsername.setNextFocusableComponent(tfRegisterEmail);

        lblRegisterEmail.setText("Email");

        tfRegisterEmail.setToolTipText("someone@somewhere.something");
        tfRegisterEmail.setNextFocusableComponent(pfRegisterPassword);

        lblRegisterPassword.setText("Password");

        pfRegisterPassword.setNextFocusableComponent(tfRegisterBankName);

        bRegister.setText("Register");
        bRegister.setEnabled(false);
        bRegister.setNextFocusableComponent(tfRegisterUsername);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblBankName.setText("Bank name");

        tfRegisterBankName.setNextFocusableComponent(ftfRegisterBalance);

        lblBalance.setText("Balance");

        ftfRegisterBalance.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        ftfRegisterBalance.setToolTipText("Please enter a number.");
        ftfRegisterBalance.setNextFocusableComponent(ftfRegisterSavingsBalance);

        lblSavingsBalance.setText("Savings balance");

        ftfRegisterSavingsBalance.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        ftfRegisterSavingsBalance.setToolTipText("Please enter a number.");
        ftfRegisterSavingsBalance.setNextFocusableComponent(bRegister);

        lblRegisterBalanceEuro.setText("€");

        lblRegisterSavingsEuro.setText("€");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblRegisterUsername)
                            .addComponent(lblRegisterEmail)
                            .addComponent(lblRegisterPassword)
                            .addComponent(pfRegisterPassword)
                            .addComponent(tfRegisterUsername)
                            .addComponent(tfRegisterEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblBankName)
                                    .addComponent(lblBalance)
                                    .addComponent(lblSavingsBalance)
                                    .addComponent(tfRegisterBankName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ftfRegisterSavingsBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblRegisterSavingsEuro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(4, 4, 4)))
                                .addContainerGap(20, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ftfRegisterBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblRegisterBalanceEuro)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRegisterInformation)
                            .addComponent(bRegister))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblRegisterUsername)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfRegisterUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblRegisterEmail)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfRegisterEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblRegisterPassword)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pfRegisterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBankName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfRegisterBankName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblBalance)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftfRegisterBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRegisterBalanceEuro))
                        .addGap(18, 18, 18)
                        .addComponent(lblSavingsBalance)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftfRegisterSavingsBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRegisterSavingsEuro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(lblRegisterInformation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bRegister)
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bRegister;
    private javax.swing.JFormattedTextField ftfRegisterBalance;
    private javax.swing.JFormattedTextField ftfRegisterSavingsBalance;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBalance;
    private javax.swing.JLabel lblBankName;
    private javax.swing.JLabel lblRegisterBalanceEuro;
    private javax.swing.JLabel lblRegisterEmail;
    private javax.swing.JLabel lblRegisterInformation;
    private javax.swing.JLabel lblRegisterPassword;
    private javax.swing.JLabel lblRegisterSavingsEuro;
    private javax.swing.JLabel lblRegisterUsername;
    private javax.swing.JLabel lblSavingsBalance;
    private javax.swing.JPasswordField pfRegisterPassword;
    private javax.swing.JTextField tfRegisterBankName;
    private javax.swing.JTextField tfRegisterEmail;
    private javax.swing.JTextField tfRegisterUsername;
    // End of variables declaration//GEN-END:variables
}

class JTextFieldLimit extends PlainDocument {

    private int limit;

    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    JTextFieldLimit(int limit, boolean upper) {
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }
        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}
