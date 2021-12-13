package application.view;

import application.model.AccountType;
import application.model.UserList;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.EventListener;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @author ed
 */
public class DashboardView extends JPanel implements Observer {

    private UserList db;
    JFreeChart chartAssetAccounts, chartCategories, chartRevenueAccounts;
    DefaultPieDataset expenseCategoriesDataset;
    DefaultCategoryDataset revenueAccountsDataset;
    TimeSeriesCollection assetAccountsDataset;

    public DashboardView() {
        initComponents();
        instantiateDashboard();
    }

    /**
     *
     * @param db UserList
     */
    public void setModel(UserList db) {
        this.db = db;
        db.addObserver(this);
    }

    /**
     *
     * @param el EventListener
     */
    public void setController(EventListener el) {
        bLogout.addActionListener((ActionListener) el);
    }

    /**
     *
     * @param o Observable
     * @param arg Object
     */
    public void update(Observable o, Object arg) {
        System.out.println("Dashboard view: updated");

        if (db.getLoggedInUser() != null) {

            // set welcome label
            this.lblWelcomeUser.setText("<html> Welcome, <b>" + db.getLoggedInUser().getUsername() + "</b></html>");
            // set networth value
            this.lblDashboardNetWorth.setText("€" + db.getLoggedInUser().getNetWorth());

            // clear previous series
            assetAccountsDataset.removeAllSeries();
            expenseCategoriesDataset.clear();
            revenueAccountsDataset.clear();

            // update asset accounts chart
            db.getLoggedInUser().getAccounts().stream().filter(acc -> (acc.getAccountType().equals(AccountType.ASSET))).forEachOrdered(acc -> {
                // declare series according to accounts
                TimeSeries series = new TimeSeries(acc.getName());
                // add series to dataset
                assetAccountsDataset.addSeries(series);

                // iterate Map<Date, String>
                Set keys = acc.getBalanceHistory().keySet();
                for (Iterator i = keys.iterator(); i.hasNext();) {
                    // iterator key
                    LocalDate key = (LocalDate) i.next();
                    // value instance
                    String value = (String) acc.getBalanceHistory().get(key);
                    // time instance
                    RegularTimePeriod rtp = new Day(Date.from(key.atStartOfDay().toInstant(ZoneOffset.UTC)));
                    // add values to series
                    series.add(rtp, Double.valueOf(value));

                }
            });

            // update expense categories chart
            db.getLoggedInUser().getAllCategories().stream().filter(s -> (!s.toString().equals("Opening balance") && !s.toString().equals("Salary") && !s.toString().equals("Investments") && !s.toString().equals("Savings") && !s.toString().equals("(no category)"))).forEachOrdered(s -> {
                // update dataset values
                expenseCategoriesDataset.setValue(s.toString() + " €" + db.getLoggedInUser().getCategoryTotalByCategory(s.toString()), Double.valueOf(db.getLoggedInUser().getCategoryTotalByCategory(s.toString())));
            });

            // update revenue accounts chart
            db.getLoggedInUser().getAccounts().stream().filter(acc -> (acc.getAccountType().equals(AccountType.REVENUE))).forEachOrdered(acc -> {
                // update dataset values
                revenueAccountsDataset.setValue(Math.abs(Double.valueOf(acc.getBalance())), acc.getName(), "");
            });
        }

    }

    /**
     * Instantiate Dashboard content
     */
    public void instantiateDashboard() {
        // declare dataset variables
        assetAccountsDataset = new TimeSeriesCollection();
        expenseCategoriesDataset = new DefaultPieDataset();
        revenueAccountsDataset = new DefaultCategoryDataset();

        // asset accounts timeseries chart
        chartAssetAccounts = ChartFactory.createTimeSeriesChart("", "", "EUR (€)", assetAccountsDataset);
        ChartPanel cpAssetAccounts = new ChartPanel(chartAssetAccounts);

        Plot plot = chartAssetAccounts.getPlot();
        AbstractRenderer renderer = (AbstractRenderer) chartAssetAccounts.getXYPlot().getRenderer();
        renderer.setDefaultStroke(new BasicStroke(2.0f));
        renderer.setAutoPopulateSeriesStroke(false);

        // add asset accounts chart to panel
        accountsChartPanel.setLayout(new java.awt.BorderLayout());
        accountsChartPanel.add(cpAssetAccounts, BorderLayout.CENTER);
        accountsChartPanel.validate();

        // categories pie chart
        chartCategories = ChartFactory.createPieChart("", expenseCategoriesDataset, true, true, false);
        ChartPanel cpExpenseCategories = new ChartPanel(chartCategories);

        // add categories chart to panel
        categoriesChartPanel.setLayout(new java.awt.BorderLayout());
        categoriesChartPanel.add(cpExpenseCategories, BorderLayout.CENTER);
        categoriesChartPanel.validate();

        chartRevenueAccounts = ChartFactory.createBarChart("", "", "EUR (€)", revenueAccountsDataset);
        ChartPanel cpRevenueAccounts = new ChartPanel(chartRevenueAccounts);

        // add revenue accounts to panel
        revenueAccountsChartPanel.setLayout(new java.awt.BorderLayout());
        revenueAccountsChartPanel.add(cpRevenueAccounts, BorderLayout.CENTER);
        revenueAccountsChartPanel.validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        pDashboardBase = new javax.swing.JPanel();
        networthPanel = new javax.swing.JPanel();
        lblDashboardNetWorth = new javax.swing.JLabel();
        bLogout = new javax.swing.JButton();
        lblWelcomeUser = new javax.swing.JLabel();
        accountsChartPanel = new javax.swing.JPanel();
        categoriesChartPanel = new javax.swing.JPanel();
        revenueAccountsChartPanel = new javax.swing.JPanel();

        setName("Dashboard"); // NOI18N

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(954, 1568));

        pDashboardBase.setPreferredSize(new java.awt.Dimension(935, 2566));

        networthPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Net Worth", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP));
        networthPanel.setName("Net Worth"); // NOI18N

        lblDashboardNetWorth.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDashboardNetWorth.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDashboardNetWorth.setText("1,000.100");

        javax.swing.GroupLayout networthPanelLayout = new javax.swing.GroupLayout(networthPanel);
        networthPanel.setLayout(networthPanelLayout);
        networthPanelLayout.setHorizontalGroup(
            networthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(networthPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDashboardNetWorth, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addContainerGap())
        );
        networthPanelLayout.setVerticalGroup(
            networthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(networthPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDashboardNetWorth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bLogout.setText("Logout");

        lblWelcomeUser.setText("Welcome, ");

        accountsChartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Asset accounts"));

        javax.swing.GroupLayout accountsChartPanelLayout = new javax.swing.GroupLayout(accountsChartPanel);
        accountsChartPanel.setLayout(accountsChartPanelLayout);
        accountsChartPanelLayout.setHorizontalGroup(
            accountsChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        accountsChartPanelLayout.setVerticalGroup(
            accountsChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
        );

        categoriesChartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Expense categories"));
        categoriesChartPanel.setToolTipText("");

        javax.swing.GroupLayout categoriesChartPanelLayout = new javax.swing.GroupLayout(categoriesChartPanel);
        categoriesChartPanel.setLayout(categoriesChartPanelLayout);
        categoriesChartPanelLayout.setHorizontalGroup(
            categoriesChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        categoriesChartPanelLayout.setVerticalGroup(
            categoriesChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 236, Short.MAX_VALUE)
        );

        revenueAccountsChartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Revenue accounts")));

        javax.swing.GroupLayout revenueAccountsChartPanelLayout = new javax.swing.GroupLayout(revenueAccountsChartPanel);
        revenueAccountsChartPanel.setLayout(revenueAccountsChartPanelLayout);
        revenueAccountsChartPanelLayout.setHorizontalGroup(
            revenueAccountsChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        revenueAccountsChartPanelLayout.setVerticalGroup(
            revenueAccountsChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 236, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pDashboardBaseLayout = new javax.swing.GroupLayout(pDashboardBase);
        pDashboardBase.setLayout(pDashboardBaseLayout);
        pDashboardBaseLayout.setHorizontalGroup(
            pDashboardBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDashboardBaseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDashboardBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(accountsChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pDashboardBaseLayout.createSequentialGroup()
                        .addGroup(pDashboardBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pDashboardBaseLayout.createSequentialGroup()
                                .addComponent(bLogout)
                                .addGap(18, 18, 18)
                                .addComponent(lblWelcomeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(networthPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(803, Short.MAX_VALUE))
                    .addComponent(categoriesChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(revenueAccountsChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pDashboardBaseLayout.setVerticalGroup(
            pDashboardBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDashboardBaseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDashboardBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bLogout)
                    .addComponent(lblWelcomeUser))
                .addGap(18, 18, 18)
                .addComponent(networthPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(accountsChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(categoriesChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(revenueAccountsChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1637, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(pDashboardBase);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1052, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel accountsChartPanel;
    private javax.swing.JButton bLogout;
    private javax.swing.JPanel categoriesChartPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDashboardNetWorth;
    private javax.swing.JLabel lblWelcomeUser;
    private javax.swing.JPanel networthPanel;
    private javax.swing.JPanel pDashboardBase;
    private javax.swing.JPanel revenueAccountsChartPanel;
    // End of variables declaration//GEN-END:variables
}
