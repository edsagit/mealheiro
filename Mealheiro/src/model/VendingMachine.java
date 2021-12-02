package model;

import java.util.*;

public class VendingMachine extends Observable implements Observer {

    private String location;

    private ArrayList<Product> availableProducts;

    private ArrayList<Sale> sales;

    public VendingMachine(String location) {
        this.location = location;
        this.availableProducts = new ArrayList<>();
        this.sales = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return new ArrayList(availableProducts);
    }

    public List<Sale> getSales() {
        return this.sales;
    }

    public void addProduct(Product p) {
        this.availableProducts.add(p);
        p.addObserver(this);
        setChanged();
        notifyObservers();
    }

    public boolean sell(Product p) {
        boolean canSell = false;
        if (this.availableProducts.contains(p)) {
            if (p.getAvailableQuantity() > 0) {
                Sale s = new Sale(new Date(), p, 1);
                this.sales.add(s);
                p.setAvailableQuantity(p.getAvailableQuantity() - 1);
                setChanged();
                canSell = true;
            }
            if (p.getAvailableQuantity() == 0) {
                this.availableProducts.remove(p);
                setChanged();
            }
        }
        notifyObservers();
        return canSell;
    }

    @Override
    public void update(Observable o, Object arg) {
        // Um produto foi modificado, notificar
        setChanged();
        notifyObservers();
    }
}
