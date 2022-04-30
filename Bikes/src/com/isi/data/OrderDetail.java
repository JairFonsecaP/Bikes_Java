package com.isi.data;

public class OrderDetail {
	private Product product;
	private Order order;
	private int quantity;
	private double unitPrice;
	public OrderDetail(Product product, Order order, int quantity, double unitPrice) {
		super();
		this.product = product;
		this.order = order;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
}
