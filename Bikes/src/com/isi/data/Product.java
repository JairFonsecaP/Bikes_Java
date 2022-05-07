package com.isi.data;

import java.text.NumberFormat;

import com.isi.util.CurrencyHelper;

public class Product {
	private int id;
	private String name;
	private String description;
	private double price;
	private int stock;
	private int sold;
	private String image;
	private Brand brand;
	private Category category;
	private NumberFormat currencyFormat;

	public Product(int id, String name, String description, double price, int stock, int sold, String image,
			Brand brand, Category category) {

		currencyFormat = CurrencyHelper.getCurrencyFormat();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.sold = sold;
		this.image = image;
		this.brand = brand;
		this.category = category;
	}
	public Product(String name, String description, double price, int stock, String image,
			Brand brand, Category category) {

		currencyFormat = CurrencyHelper.getCurrencyFormat();
		this.id = 0;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.sold = 0;
		this.image = image;
		this.brand = brand;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getPriceFormated() {
		return currencyFormat.format(price);
	}

}
