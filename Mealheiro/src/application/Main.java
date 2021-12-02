/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package application;

import controller.*;
import model.*;


/**
 *
 * @author ed
 */
public class Main {
    
    private static Database db;
    
    public Main() {
        // simulate data, could be loaded from database
        
        db = new Database();
        
//        VendingMachine vendingMachine = new VendingMachine("DEI");
//        vendingMachine.addProduct(new Product("Sumo", 100, 5));
//        vendingMachine.addProduct(new Product("Água", 50, 5));
//        vendingMachine.addProduct(new Product("Bolo", 120, 5));
//        vendingMachine.addProduct(new Product("Chocolate", 150, 5));

        MainController mc = new MainController();
        mc.setModel(db);
        mc.displayView();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Main();
    }
    
}
