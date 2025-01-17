package application.view;

import application.controller.TransactionsController;
import application.model.Account;
import application.model.TransactionType;
import application.model.UserList;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ed
 */
public class TransactionsView extends JPanel implements Observer {

    private UserList db;
    private DefaultTableModel expenseModel;
    private DefaultTableModel incomeModel;
    private DefaultTableModel transferModel;

    /**
     * Creates new form TransactionsView
     */
    public TransactionsView(UserList model) {
        TransactionsController tc = new TransactionsController(model, this);
        // set view model
        this.db = model;
        // set as observer
        this.db.addObserver(this);
        // tables data placeholder
        Object[][] data = {};

        // expense table headers
        String[] tableColumnNames = {"Description", "Amount", "Date", "Source account", "Destination account", "Category"};

        // expense table model
        expenseModel = new DefaultTableModel(data, tableColumnNames);

        // income table model
        incomeModel = new DefaultTableModel(data, tableColumnNames);

        // transfer table model
        transferModel = new DefaultTableModel(data, tableColumnNames);

        initComponents();
        
        // add submit transaction buttons action listener to transactions controller
        bTransactionSubmit.addActionListener(tc);
        bTransactionUndo.addActionListener(tc);
        bTransactionRedo.addActionListener(tc);
        
        // set text field to current date
        ftfTransactionDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        // set expense table sorterExpense
        TableRowSorter<TableModel> sorterExpense = new TableRowSorter<TableModel>(expenseTable.getModel());
        // set expense table sorter
        expenseTable.setRowSorter(sorterExpense);

        List<RowSorter.SortKey> sortKeysExpense = new ArrayList<>(25);
        sortKeysExpense.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeysExpense.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        // set sortKeys
        sorterExpense.setSortKeys(sortKeysExpense);

        // set income table sorterIncome
        TableRowSorter<TableModel> sorterIncome = new TableRowSorter<TableModel>(incomeTable.getModel());
        // set income table sorter
        incomeTable.setRowSorter(sorterIncome);

        List<RowSorter.SortKey> sortKeysIncome = new ArrayList<>(25);
        sortKeysIncome.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeysIncome.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        // set sortKeys
        sorterIncome.setSortKeys(sortKeysIncome);

        // set transfer table sorterTransfer
        TableRowSorter<TableModel> sorterTransfer = new TableRowSorter<TableModel>(transferTable.getModel());
        // set table transfer sorter
        transferTable.setRowSorter(sorterTransfer);

        List<RowSorter.SortKey> sortKeysTransfer = new ArrayList<>(25);
        sortKeysTransfer.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeysTransfer.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        // set sortKeys
        sorterIncome.setSortKeys(sortKeysTransfer);
    }

    /**
     *
     * @param o Observable
     * @param arg Object
     */
    public void update(Observable o, Object arg) {
        // if loggedIn populate the table with user transactions data
        if (db.getLoggedInUser() != null) {
             System.out.println("Transaction view: updated");
            // clear combo boxes
            cbTransactionSourceAccount.removeAllItems();
            cbTransactionDestinationAccount.removeAllItems();

            // clear the tables
            expenseModel.setRowCount(0);
            incomeModel.setRowCount(0);
            transferModel.setRowCount(0);
            
            db.getLoggedInUser().getTransactions().forEach(tra -> {
                // populate table
                switch (tra.getType()) {
                    case WITHDRAWAL ->
                        expenseModel.addRow(new Object[]{tra.getDescription(), "€" + tra.getAmount(), tra.getDate(), tra.getSourceAccount().getName(), tra.getDestinationAccount().getName(), tra.getCategory()});

                    case DEPOSIT ->
                        incomeModel.addRow(new Object[]{tra.getDescription(), "€" + tra.getAmount(), tra.getDate(), tra.getSourceAccount().getName(), tra.getDestinationAccount().getName(), tra.getCategory()});

                    case TRANSFER ->
                        transferModel.addRow(new Object[]{tra.getDescription(), "€" + tra.getAmount(), tra.getDate(), tra.getSourceAccount().getName(), tra.getDestinationAccount().getName(), tra.getCategory()});

                }
            });

            // declare comboboxes models
            ComboBoxModel<String> modelSource = cbTransactionSourceAccount.getModel();
            ComboBoxModel<String> modelDestination = cbTransactionDestinationAccount.getModel();

            // check if instances of the models
            if (modelSource instanceof DefaultComboBoxModel dcbmS && modelDestination instanceof DefaultComboBoxModel dcbmD) {
                // remove elements first
                dcbmS.removeAllElements();
                dcbmD.removeAllElements();
                // add accounts names

                if (getCbTransactionType().equals(TransactionType.WITHDRAWAL)) {
                    for (String s : db.getLoggedInUser().getAssetAccountsNames()) {
                        dcbmS.addElement(s);
                    }
                    for (String s : db.getLoggedInUser().getExpenseAccountsNames()) {
                        dcbmD.addElement(s);
                    }
                }

                if (getCbTransactionType().equals(TransactionType.DEPOSIT)) {
                    for (String s : db.getLoggedInUser().getRevenueAccountsNames()) {
                        dcbmS.addElement(s);
                    }
                    for (String s : db.getLoggedInUser().getAssetAccountsNames()) {
                        dcbmD.addElement(s);
                    }
                }

                if (getCbTransactionType().equals(TransactionType.TRANSFER)) {
                    for (String s : db.getLoggedInUser().getAssetAccountsNames()) {
                        dcbmS.addElement(s);
                    }
                    for (String s : db.getLoggedInUser().getAssetAccountsNames()) {
                        dcbmD.addElement(s);
                    }
                }
            }
        }
        clearFields();
        validate();
    }

    /**
     *
     * @return Account acc - Return Object Account destination account from
     * comboBox, or return null if none corresponds
     */
    public Account getCbTransactionDestinationAccount() {
        Account acc = null;
        String s = cbTransactionDestinationAccount.getSelectedItem().toString();
        for (Account a : db.getLoggedInUser().getAccounts()) {
            if (a.getName().equals(s)) {
                return acc = a;
            }
        }
        return acc;
    }

    /**
     *
     * @return Account acc - Return object Account source account from comboBox,
     * or return null if none corresponds
     */
    public Account getCbTransactionSourceAccount() {
        Account acc = null;
        String s = cbTransactionSourceAccount.getSelectedItem().toString();
        for (Account a : db.getLoggedInUser().getAccounts()) {
            if (a.getName().equals(s)) {
                return acc = a;
            }
        }
        return acc;
    }

    /**
     *
     * @return TransactionType tt - Return object TransactionType from comboBox,
     * or return null if none corresponds
     */
    public TransactionType getCbTransactionType() {
        TransactionType tt = null;
        switch (cbTransactionType.getSelectedItem().toString()) {
            case "Expense" -> {
                return tt = TransactionType.WITHDRAWAL;
            }
            case "Income" -> {
                return tt = TransactionType.DEPOSIT;
            }
            case "Transfer" -> {
                return tt = TransactionType.TRANSFER;
            }
        }
        return tt;
    }

    /**
     *
     * @return String amount
     */
    public String getFtfTransactionAmount() {
        if (!ftfTransactionAmount.getText().isEmpty() || !ftfTransactionAmount.getText().isBlank()) {
            return ftfTransactionAmount.getText();
        }
        return null;
    }

    /**
     *
     * @return LocalDate date
     */
    public LocalDate getFtfTransactionDate() {
        return LocalDate.parse(ftfTransactionDate.getText());
    }

    /**
     *
     * @return String category
     */
    public String getTfTransactionCategory() {
        if (!tfTransactionCategory.getText().isEmpty() || !tfTransactionCategory.getText().isBlank()) {
            return tfTransactionCategory.getText();
        }
        return "(no category)";
    }

    /**
     *
     * @return String description
     */
    public String getTfTransactionDescription() {
        if (!tfTransactionDescription.getText().isEmpty() || !tfTransactionDescription.getText().isBlank()) {
            return tfTransactionDescription.getText();
        }
        return "(no description)";
    }

    /**
     *
     * @param s String - Set label transactionInformation text with String s
     */
    public void setLblTransactionInformation(String s) {
        this.lblTransactionInformation.setText("<html>" + s + "</html>");
    }

    /**
     *
     * @param b boolean - Value to set button with
     */
    public void setUndoButtonEnabled(boolean b) {
        bTransactionUndo.setEnabled(b);
    }

    /**
     *
     * @param b boolean - Value to set button with
     */
    public void setRedoButtonEnabled(boolean b) {
        bTransactionRedo.setEnabled(b);
    }

    /**
     * Clear all input fields from the form
     */
    public void clearFields() {
        this.ftfTransactionAmount.setValue(null);
        this.ftfTransactionDate.setValue(new Date());
        this.tfTransactionCategory.setText("");
        this.tfTransactionDescription.setText("");
        this.cbTransactionType.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        tpTransactions = new javax.swing.JTabbedPane();
        expensePane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        expenseTable = new javax.swing.JTable();
        incomePane = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        incomeTable = new javax.swing.JTable();
        transferPane = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        transferTable = new javax.swing.JTable();
        newTransactionPane = new javax.swing.JPanel();
        bTransactionSubmit = new javax.swing.JButton();
        tfTransactionDescription = new javax.swing.JTextField();
        lblTransactionDescription = new javax.swing.JLabel();
        cbTransactionSourceAccount = new javax.swing.JComboBox<>();
        lblTransactionSourceAccount = new javax.swing.JLabel();
        lblTransactionDestinationAccount = new javax.swing.JLabel();
        cbTransactionDestinationAccount = new javax.swing.JComboBox<>();
        ftfTransactionDate = new javax.swing.JFormattedTextField();
        lblTransactionDate = new javax.swing.JLabel();
        lblTransactionAmount = new javax.swing.JLabel();
        ftfTransactionAmount = new javax.swing.JFormattedTextField();
        lblTransactionCategory = new javax.swing.JLabel();
        lblTransactionType = new javax.swing.JLabel();
        cbTransactionType = new javax.swing.JComboBox<>();
        lblEuroSymbol = new javax.swing.JLabel();
        tfTransactionCategory = new javax.swing.JTextField();
        lblTransactionInformation = new javax.swing.JLabel();
        bTransactionUndo = new javax.swing.JButton();
        bTransactionRedo = new javax.swing.JButton();

        setName("Transactions"); // NOI18N

        expensePane.setName(""); // NOI18N

        expenseTable.setModel(expenseModel);
        expenseTable.setPreferredSize(new java.awt.Dimension(0, 2500));
        jScrollPane1.setViewportView(expenseTable);

        javax.swing.GroupLayout expensePaneLayout = new javax.swing.GroupLayout(expensePane);
        expensePane.setLayout(expensePaneLayout);
        expensePaneLayout.setHorizontalGroup(
            expensePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(expensePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                .addContainerGap())
        );
        expensePaneLayout.setVerticalGroup(
            expensePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(expensePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                .addContainerGap())
        );

        tpTransactions.addTab("Expense", expensePane);

        incomeTable.setModel(incomeModel);
        jScrollPane2.setViewportView(incomeTable);

        javax.swing.GroupLayout incomePaneLayout = new javax.swing.GroupLayout(incomePane);
        incomePane.setLayout(incomePaneLayout);
        incomePaneLayout.setHorizontalGroup(
            incomePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(incomePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                .addContainerGap())
        );
        incomePaneLayout.setVerticalGroup(
            incomePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(incomePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                .addContainerGap())
        );

        tpTransactions.addTab("Income", incomePane);

        transferTable.setModel(transferModel);
        jScrollPane3.setViewportView(transferTable);

        javax.swing.GroupLayout transferPaneLayout = new javax.swing.GroupLayout(transferPane);
        transferPane.setLayout(transferPaneLayout);
        transferPaneLayout.setHorizontalGroup(
            transferPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transferPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                .addContainerGap())
        );
        transferPaneLayout.setVerticalGroup(
            transferPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transferPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                .addContainerGap())
        );

        tpTransactions.addTab("Transfer", transferPane);

        jSplitPane1.setRightComponent(tpTransactions);

        bTransactionSubmit.setText("Submit");

        lblTransactionDescription.setText("Description");

        lblTransactionSourceAccount.setText("Source account");

        lblTransactionDestinationAccount.setText("Destination account");

        ftfTransactionDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        ftfTransactionDate.setToolTipText("Format: 6 Dec 2021");

        lblTransactionDate.setText("Date");

        lblTransactionAmount.setText("Amount");

        ftfTransactionAmount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.00"))));

        lblTransactionCategory.setText("Category");

        lblTransactionType.setText("Type");

        cbTransactionType.setModel(new javax.swing.DefaultComboBoxModel<>(TransactionType.getTransactionTypes()));
        cbTransactionType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTransactionTypeActionPerformed(evt);
            }
        });

        lblEuroSymbol.setText("€");

        bTransactionUndo.setText("Undo");
        bTransactionUndo.setEnabled(false);

        bTransactionRedo.setText("Redo");
        bTransactionRedo.setEnabled(false);

        javax.swing.GroupLayout newTransactionPaneLayout = new javax.swing.GroupLayout(newTransactionPane);
        newTransactionPane.setLayout(newTransactionPaneLayout);
        newTransactionPaneLayout.setHorizontalGroup(
            newTransactionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newTransactionPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newTransactionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newTransactionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(newTransactionPaneLayout.createSequentialGroup()
                            .addComponent(bTransactionUndo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bTransactionRedo))
                        .addComponent(lblTransactionDescription, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tfTransactionDescription, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbTransactionSourceAccount, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTransactionSourceAccount, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTransactionDestinationAccount, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbTransactionDestinationAccount, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTransactionDate, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ftfTransactionDate, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTransactionAmount, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTransactionCategory, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTransactionType, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbTransactionType, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, newTransactionPaneLayout.createSequentialGroup()
                            .addComponent(ftfTransactionAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblEuroSymbol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(tfTransactionCategory, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bTransactionSubmit))
                    .addComponent(lblTransactionInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        newTransactionPaneLayout.setVerticalGroup(
            newTransactionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newTransactionPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTransactionAmount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newTransactionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftfTransactionAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEuroSymbol))
                .addGap(18, 18, 18)
                .addComponent(lblTransactionSourceAccount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTransactionSourceAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTransactionDestinationAccount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTransactionDestinationAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTransactionDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftfTransactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTransactionType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTransactionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTransactionCategory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTransactionCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTransactionDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTransactionDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bTransactionSubmit)
                .addGap(18, 18, 18)
                .addGroup(newTransactionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bTransactionUndo)
                    .addComponent(bTransactionRedo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTransactionInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(newTransactionPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbTransactionTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTransactionTypeActionPerformed
        if (db.getLoggedInUser() != null) {
            // declare comboboxes models
            ComboBoxModel<String> modelSource = cbTransactionSourceAccount.getModel();
            ComboBoxModel<String> modelDestination = cbTransactionDestinationAccount.getModel();

            // check if instances of the models
            if (modelSource instanceof DefaultComboBoxModel dcbmS && modelDestination instanceof DefaultComboBoxModel dcbmD) {
                // remove elements first
                dcbmS.removeAllElements();
                dcbmD.removeAllElements();
                // add accounts names

                if (getCbTransactionType().equals(TransactionType.WITHDRAWAL)) {
                    for (String s : db.getLoggedInUser().getAssetAccountsNames()) {
                        dcbmS.addElement(s);
                    }
                    for (String s : db.getLoggedInUser().getExpenseAccountsNames()) {
                        dcbmD.addElement(s);
                    }
                }

                if (getCbTransactionType().equals(TransactionType.DEPOSIT)) {
                    for (String s : db.getLoggedInUser().getRevenueAccountsNames()) {
                        dcbmS.addElement(s);
                    }
                    for (String s : db.getLoggedInUser().getAssetAccountsNames()) {
                        dcbmD.addElement(s);
                    }
                }

                if (getCbTransactionType().equals(TransactionType.TRANSFER)) {
                    for (String s : db.getLoggedInUser().getAssetAccountsNames()) {
                        dcbmS.addElement(s);
                    }
                    for (String s : db.getLoggedInUser().getAssetAccountsNames()) {
                        dcbmD.addElement(s);
                    }
                }
            }
        }

    }//GEN-LAST:event_cbTransactionTypeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bTransactionRedo;
    private javax.swing.JButton bTransactionSubmit;
    private javax.swing.JButton bTransactionUndo;
    private javax.swing.JComboBox<String> cbTransactionDestinationAccount;
    private javax.swing.JComboBox<String> cbTransactionSourceAccount;
    private javax.swing.JComboBox<String> cbTransactionType;
    private javax.swing.JPanel expensePane;
    private javax.swing.JTable expenseTable;
    private javax.swing.JFormattedTextField ftfTransactionAmount;
    private javax.swing.JFormattedTextField ftfTransactionDate;
    private javax.swing.JPanel incomePane;
    private javax.swing.JTable incomeTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblEuroSymbol;
    private javax.swing.JLabel lblTransactionAmount;
    private javax.swing.JLabel lblTransactionCategory;
    private javax.swing.JLabel lblTransactionDate;
    private javax.swing.JLabel lblTransactionDescription;
    private javax.swing.JLabel lblTransactionDestinationAccount;
    private javax.swing.JLabel lblTransactionInformation;
    private javax.swing.JLabel lblTransactionSourceAccount;
    private javax.swing.JLabel lblTransactionType;
    private javax.swing.JPanel newTransactionPane;
    private javax.swing.JTextField tfTransactionCategory;
    private javax.swing.JTextField tfTransactionDescription;
    private javax.swing.JTabbedPane tpTransactions;
    private javax.swing.JPanel transferPane;
    private javax.swing.JTable transferTable;
    // End of variables declaration//GEN-END:variables
}
