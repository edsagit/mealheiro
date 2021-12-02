package view;

import controller.*;
import model.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class ReStockProductsView extends JPanel implements Observer {

    private VendingMachine vm;
    private JPanel productsPanel;
    private JButton fecharButton;
    private Hashtable<Product, JSpinner> hashtable;
    private EventListener eventListener;

    public ReStockProductsView() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // painel com opcoes
        this.productsPanel = new JPanel();
        this.productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
        this.productsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botao para fechar
        fecharButton = new JButton("Fechar");
        fecharButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // adicionar tudo ao painel principal
        this.add(productsPanel);
        this.add(Box.createRigidArea(new Dimension(5, 50)));
        this.add(fecharButton);
        this.hashtable = new Hashtable();
    }

    public void setModel(VendingMachine vm) {
        this.vm = vm;
        vm.addObserver(this);

        // Chamar update manualmente para inicializar a vista
        this.update(vm, null);
    }

    public void setController(EventListener el) {
        this.eventListener = el;

        this.fecharButton.addActionListener((ActionListener) el);
        // Chamar update para garantir que os listeners dos spinners estao definidos
        if (this.vm != null) {
            this.update(this.vm, null);
        }
    }

    public void update(Observable o, Object arg) {

        // desligar o controller dos spinners
        for (JSpinner js : this.hashtable.values()) {
            js.removeChangeListener((ChangeListener) this.eventListener);
        }

        this.productsPanel.removeAll();
        this.hashtable.clear();

        for (Product p : this.vm.getProducts()) {
            JPanel panel = new JPanel();
            panel.add(new JLabel(p.getName()));
            JSpinner js = new JSpinner();
            js.setValue(p.getAvailableQuantity());
            //js.setActionCommand(p.getName());
            js.addChangeListener((ChangeListener) this.eventListener);
            panel.add(js);
            productsPanel.add(panel);
            this.hashtable.put(p, js);
        }

        this.validate();
        this.repaint();

    }

    public int getProductQuantity(Product p) {
        return (Integer) this.hashtable.get(p).getValue();
    }

}
