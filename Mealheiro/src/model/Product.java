package model;

import java.util.*;

public class Product extends Observable {

    private String name;
    private int price; // centimos

    private int availableQuantity;

    public Product(String name, int price, int qtd) {
        this.name = name;
        this.price = price;
        this.availableQuantity = qtd;
    }

    public void setAvailableQuantity(int qtd) {
        if (this.availableQuantity != qtd) {
            setChanged();
        }
        this.availableQuantity = qtd;
        notifyObservers();
    }

    public int getAvailableQuantity() {
        return this.availableQuantity;
    }

    public void setName(String name) {
        if (!this.name.equals(name)) {
            setChanged();
        }
        this.name = name;

        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public void setPrice(int price) {
        if (this.price != price) {
            setChanged();
        }
        this.price = price;
        notifyObservers();
    }

    public int getPrice() {
        return price;
    }

}
