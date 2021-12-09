package model;

/**
 *
 * @author ed
 */
public enum TransactionType {
    
    WITHDRAWAL, DEPOSIT, TRANSFER, OPENING_BALANCE;
    
    public static String[] getTransactionTypes() {
        String[] types = {"Expense", "Income", "Transfer"};
        return types;
    }
}

