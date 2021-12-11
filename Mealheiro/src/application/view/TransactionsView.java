package application.view;

import application.model.UserList;
import application.model.TransactionType;
import application.model.Account;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
    DefaultTableModel expenseModel;
    DefaultTableModel incomeModel;
    DefaultTableModel transferModel;

    /**
     * Creates new form TransactionsView
     */
    public TransactionsView() {

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

        // set text field to current date
        ftfTransactionDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        // set expense table sorterExpense
        TableRowSorter<TableModel> sorterExpense = new TableRowSorter<TableModel>(expenseTable.getModel());
        expenseTable.setRowSorter(sorterExpense);

        List<RowSorter.SortKey> sortKeysExpense = new ArrayList<>(25);
        sortKeysExpense.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeysExpense.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorterExpense.setSortKeys(sortKeysExpense);

        // set income table sorterIncome
        TableRowSorter<TableModel> sorterIncome = new TableRowSorter<TableModel>(incomeTable.getModel());
        incomeTable.setRowSorter(sorterIncome);

        List<RowSorter.SortKey> sortKeysIncome = new ArrayList<>(25);
        sortKeysIncome.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeysIncome.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorterIncome.setSortKeys(sortKeysIncome);

        // set transfer table sorterTransfer
        TableRowSorter<TableModel> sorterTransfer = new TableRowSorter<TableModel>(transferTable.getModel());
        transferTable.setRowSorter(sorterTransfer);

        List<RowSorter.SortKey> sortKeysTransfer = new ArrayList<>(25);
        sortKeysTransfer.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeysTransfer.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorterIncome.setSortKeys(sortKeysTransfer);

    }

    public void setModel(UserList db) {
        this.db = db;
        db.addObserver(this);
    }

    public void setController(EventListener el) {
        bTransactionSubmit.addActionListener((ActionListener) el);
    }

    public void update(Observable o, Object arg) {
        System.out.println("Transaction view: updated");
        // clear the tables
        expenseModel.setRowCount(0);
        incomeModel.setRowCount(0);
        transferModel.setRowCount(0);

        // if loggedIn populate the table with user data
        if (db.getLoggedInUser() != null) {
            db.getLoggedInUser().getTransactions().forEach(tra -> {
                // populate table
                switch (tra.getType()) {
                    case WITHDRAWAL ->
                        expenseModel.addRow(new Object[]{tra.getDescription(), "-€" + tra.getAmount(), tra.getDate(), tra.getSourceAccount().getName(), tra.getDestinationAccount().getName(), tra.getCategory()});

                    case DEPOSIT ->
                        incomeModel.addRow(new Object[]{tra.getDescription(), "+€" + tra.getAmount(), tra.getDate(), tra.getSourceAccount().getName(), tra.getDestinationAccount().getName(), tra.getCategory()});

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
                for (String s : db.getLoggedInUser().getAccountsNames()) {
                    dcbmS.addElement(s);
                    dcbmD.addElement(s);
                }
            }
        }
        clearFields();
    }

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

    public String getFtfTransactionAmount() {
        return ftfTransactionAmount.getText();
    }

    public LocalDate getFtfTransactionDate() {
        return LocalDate.parse(ftfTransactionDate.getText());
    }

    public String getTfTransactionCategory() {
        if (!tfTransactionCategory.getText().isEmpty() || !tfTransactionCategory.getText().isBlank()) {
            return tfTransactionCategory.getText();
        }
        return "(no category)";
    }

    public String getTfTransactionDescription() {
        if (!tfTransactionDescription.getText().isEmpty() || !tfTransactionDescription.getText().isBlank()) {
            return tfTransactionDescription.getText();
        }
        return "(no description)";
    }

    public void setLblTransactionInformation(String s) {
        this.lblTransactionInformation.setText(s);
    }

    public void clearFields() {
        this.ftfTransactionAmount.setValue(null);
        this.cbTransactionSourceAccount.setSelectedIndex(0);
        this.cbTransactionDestinationAccount.setSelectedIndex(1);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                .addContainerGap())
        );
        expensePaneLayout.setVerticalGroup(
            expensePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(expensePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                .addContainerGap())
        );
        incomePaneLayout.setVerticalGroup(
            incomePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(incomePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                .addContainerGap())
        );
        transferPaneLayout.setVerticalGroup(
            transferPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transferPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
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

        ftfTransactionAmount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        lblTransactionCategory.setText("Category");

        lblTransactionType.setText("Type");

        cbTransactionType.setModel(new javax.swing.DefaultComboBoxModel<>(TransactionType.getTransactionTypes()));

        lblEuroSymbol.setText("€");

        lblTransactionInformation.setText("jLabel1");

        javax.swing.GroupLayout newTransactionPaneLayout = new javax.swing.GroupLayout(newTransactionPane);
        newTransactionPane.setLayout(newTransactionPaneLayout);
        newTransactionPaneLayout.setHorizontalGroup(
            newTransactionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newTransactionPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newTransactionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newTransactionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(newTransactionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bTransactionSubmit)
                            .addGroup(newTransactionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblTransactionDescription)
                                .addComponent(tfTransactionDescription)
                                .addComponent(cbTransactionSourceAccount, 0, 160, Short.MAX_VALUE)
                                .addComponent(lblTransactionSourceAccount)
                                .addComponent(lblTransactionDestinationAccount)
                                .addComponent(cbTransactionDestinationAccount, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTransactionDate)
                                .addComponent(ftfTransactionDate)
                                .addComponent(lblTransactionAmount)
                                .addComponent(lblTransactionCategory)))
                        .addComponent(lblTransactionType)
                        .addComponent(cbTransactionType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(newTransactionPaneLayout.createSequentialGroup()
                            .addComponent(ftfTransactionAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblEuroSymbol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(tfTransactionCategory))
                    .addComponent(lblTransactionInformation))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(lblTransactionInformation)
                .addGap(18, 18, 18)
                .addComponent(bTransactionSubmit)
                .addContainerGap(190, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(newTransactionPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jSplitPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bTransactionSubmit;
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
