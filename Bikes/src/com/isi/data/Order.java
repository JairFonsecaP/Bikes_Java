package com.isi.data;

import java.text.NumberFormat;

import com.isi.util.CurrencyHelper;

public class Order {
	private int id;
	private Product product;
	private double subtotal;
	private double qst;
	private double gst;
	private double total;
	private NumberFormat currencyFormat;

	public Order(Product product) {
		this.id = 0;
		this.product = product;
		subtotal = this.product.getPrice();
		qst = this.product.getPrice() * 0.09975;
		gst = this.product.getPrice() * 0.05;
		total = this.subtotal + qst + gst;
		currencyFormat = CurrencyHelper.getCurrencyFormat();
	}
	

	public Order(int id, Product product, double subtotal, double qst, double gst, double total) {
		this.id = id;
		this.product = product;
		this.subtotal = subtotal;
		this.qst = qst;
		this.gst = gst;
		this.total = total;
		currencyFormat = CurrencyHelper.getCurrencyFormat();
	}
	
	public String getSubtotalString()
	{
		return currencyFormat.format(subtotal);
	}

	public String getQstString()
	{
		return currencyFormat.format(qst);
	}
	
	public String getGstString()
	{
		return currencyFormat.format(gst);
	}
	
	public String getTotalString()
	{
		return currencyFormat.format(total);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getQst() {
		return qst;
	}

	public void setQst(double qst) {
		this.qst = qst;
	}

	public double getGst() {
		return gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	

}
