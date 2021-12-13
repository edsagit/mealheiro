package application.model;

/**
 *
 * @author ed
 */
// Asset accounts are your personal bank accounts. 
// Expense accounts are the accounts you spend money at, such as stores and friends. 
// Revenue accounts are accounts you receive money from, such as your job, the government or other sources of income. 
// Liabilities are your debts and loans such as old credit card debts or student loans.
public enum AccountType {
    ASSET, EXPENSE, REVENUE, LIABILITY, OPENING;

    /**
     *
     * @return String[] types - Array of strings with AccountTypes values for
     * combo box
     */
    public static String[] getAccountTypes() {
        String[] types = {"Asset", "Expense", "Revenue", "Liability"};
        return types;
    }
}
