package controller;

import view.*;
import model.*;
import java.awt.event.*;

import java.util.*;

public class ProductSelectionController extends AbstractController {

    private VendingMachine vm;
    private ProductSelectionView psv;

    public ProductSelectionController() {

    }

    public void setModel(VendingMachine vm) {
        this.vm = vm;
    }

    public void setView(ProductSelectionView psv) {
        this.psv = psv;
        psv.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Pagar")) {
            String productName = this.psv.getSelectedProductName();
            System.out.println(productName);
            for (Product p : this.vm.getProducts()) {
                if (p.getName().equals(productName)) {
                    this.vm.sell(p);
                }
            }
        }
        super.actionPerformed(e);
    }
}
