package view;

import controller.*;
import model.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

public class ProductSelectionView extends JPanel implements Observer {

    private JPanel productsPanel;
    private ButtonGroup optionsGroup;
    private JButton payButton;
    private JButton fecharButton;

    private VendingMachine vm;

    public ProductSelectionView() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // painel com opcoes
        this.productsPanel = new JPanel();
        this.productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
        this.productsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botao para pagar
        payButton = new JButton("Pagar");
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botao para fechar
        fecharButton = new JButton("Fechar");
        fecharButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // adicionar tudo ao painel principal
        this.add(productsPanel);
        this.add(Box.createRigidArea(new Dimension(5, 50)));
        this.add(payButton);
        this.add(fecharButton);
    }

    public void setModel(VendingMachine vm) {
        this.vm = vm;
        vm.addObserver(this);

        // Chamar update manualmente para inicializar a vista
        this.update(vm, null);
    }

    public void setController(EventListener el) {
        payButton.addActionListener((ActionListener) el);
        fecharButton.addActionListener((ActionListener) el);
    }

    public void update(Observable o, Object arg) {

        this.productsPanel.removeAll();
        this.optionsGroup = new ButtonGroup();
        for (Product p : this.vm.getProducts()) {
            JRadioButton jr = new JRadioButton(p.getName() + "(" + p.getAvailableQuantity() + ")" + ": " + p.getPrice() + " centimos");
            jr.setActionCommand(p.getName());
            productsPanel.add(jr);
            optionsGroup.add(jr);
            //jr.setSelected(true);
        }
        optionsGroup.clearSelection();
        this.validate();
        this.repaint();

    }

    public String getSelectedProductName() {
        return this.optionsGroup.getSelection().getActionCommand();
    }

}
