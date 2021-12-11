package application.model;

/**
 *
 * @author ed
 */
public enum TransactionType {
    
    WITHDRAWAL, DEPOSIT, TRANSFER, OPENING_BALANCE;
    
    /**
     * 
     * @return String[] types - Array of strings with TransactionTypes values for combo box
     */
    public static String[] getTransactionTypes() {
        String[] types = {"Expense", "Income", "Transfer"};
        return types;
    }
}

