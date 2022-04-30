package com.isi.data;

public class Order {
	private int id;
	private double subtotal;
	private double qst;
	private double gst;
	private double total;
	
	public Order(int id, double subtotal, double qst, double gst, double total) {
		super();
		this.id = id;
		this.subtotal = subtotal;
		this.qst = qst;
		this.gst = gst;
		this.total = total;
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
	
	
}
