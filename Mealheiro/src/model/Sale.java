package model;

import java.util.Date;

public class Sale {
	private Date date;
	private Product product;
	private int qtd;
	
	public Sale(Date date, Product product, int qtd) {
		this.date = date;
		this.product = product;
		this.qtd = qtd;
	}

  public Date getDate() {
    return this.date;
  }

  public Product getProduct() {
    return this.product;
  }

  public int getQuantity() {
    return this.qtd;
  }
}
