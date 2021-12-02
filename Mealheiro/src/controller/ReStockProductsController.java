package controller;

import view.*;
import model.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;


public class ReStockProductsController extends AbstractController {

  private VendingMachine vm;
  private ReStockProductsView rspv;

  public ReStockProductsController() {

  }

  public void setModel(VendingMachine vm) {
    this.vm = vm;
  }

  public void setView(ReStockProductsView rspv) {
    this.rspv = rspv;
    rspv.setController(this);
  }



  @Override
  public void stateChanged(ChangeEvent ce) {
    System.out.println("StateChanged " + ce);

    for (Product p: this.vm.getProducts()) {
     // System.out.println
      p.setAvailableQuantity(this.rspv.getProductQuantity(p));
    }
    super.stateChanged(ce);
  }
}