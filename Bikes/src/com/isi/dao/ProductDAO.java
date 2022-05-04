package com.isi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.isi.data.Brand;
import com.isi.data.Category;
import com.isi.data.Product;

public class ProductDAO {
	private DataSource dataSource;

	public ProductDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Product> getAllProductsByCategory(int categoryId) throws SQLException {

		List<Product> products = new ArrayList<>();

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "SELECT product.Id,  "
					+ "	   product.Name, " 
					+ "    product.Desciption, " 
					+ "    product.Price, "
					+ "    product.Stock, " 
					+ "    product.Sold, " 
					+ "    product.Image, " 
					+ "    product.Brand_Id, "
					+ "    brand.name as 'BrandName', " 
					+ "    brand.image as 'BrandImage', "
					+ "    product.Category_Id, " 
					+ "    category.name as 'CategoryName', "
					+ "    category.image as 'CategoryImage' "
					+ "	   FROM product JOIN brand ON product.Brand_Id = brand.Id "
					+ "	   JOIN category ON product.Category_Id = category.Id"
					+ "	   WHERE category.id = ? ; ";

			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, categoryId);
			
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				int productId = myRs.getInt("Id");
				String productName = myRs.getString("Name");
				String productDesciption = myRs.getString("Desciption");
				double price = myRs.getDouble("Price");
				int stock = myRs.getInt("Stock");
				int sold = myRs.getInt("Sold");
				String image = myRs.getString("Image");
				int brandId = myRs.getInt("Brand_Id");
				String brandName = myRs.getString("BrandName");
				String brandImage = myRs.getString("BrandImage");
				int category_Id = myRs.getInt("Brand_Id");
				String categoryName = myRs.getString("CategoryName");
				String categoryImage = myRs.getString("CategoryImage");

				Product product = new Product(productId, productName, productDesciption, price, stock, sold, image,
						new Brand(brandId, brandName, brandImage),
						new Category(category_Id, categoryName, categoryImage));
				products.add(product);
			}
			return products;
		} finally {
			close(myConn, myStmt, myRs);
		}
	}

	public List<Product> getMostSoldProductsList() throws SQLException {

		List<Product> products = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "SELECT product.Id, "
					+ "	   product.Name, " 
					+ "    product.Desciption, " 
					+ "    product.Price, "
					+ "    product.Stock, " 
					+ "    product.Sold, " 
					+ "    product.Image, " 
					+ "    product.Brand_Id, "
					+ "    brand.name as 'BrandName', " 
					+ "    brand.image as 'BrandImage', "
					+ "    product.Category_Id, " 
					+ "    category.name as 'CategoryName', "
					+ "    category.image as 'CategoryImage' "
					+ "FROM product JOIN brand ON product.Brand_Id = brand.Id "
					+ "JOIN category ON product.Category_Id = category.Id " + "ORDER BY product.sold DESC "
					+ "LIMIT 3; ";

			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				int productId = myRs.getInt("Id");
				String productName = myRs.getString("Name");
				String productDesciption = myRs.getString("Desciption");
				double price = myRs.getDouble("Price");
				int stock = myRs.getInt("Stock");
				int sold = myRs.getInt("Sold");
				String image = myRs.getString("Image");
				int brandId = myRs.getInt("Brand_Id");
				String brandName = myRs.getString("BrandName");
				String brandImage = myRs.getString("BrandImage");
				int categoryId = myRs.getInt("Category_Id");
				String categoryName = myRs.getString("CategoryName");
				String categoryImage = myRs.getString("CategoryImage");

				Product product = new Product(productId, productName, productDesciption, price, stock, sold, image,
						new Brand(brandId, brandName, brandImage),
						new Category(categoryId, categoryName, categoryImage));
				products.add(product);
			}
			return products;
		} finally {
			close(myConn, myStmt, myRs);
		}
	}

	public List<Product> getAllProductsList() throws SQLException {

		List<Product> products = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "SELECT product.Id,  "
					+ "	   product.Name, " 
					+ "    product.Desciption, " 
					+ "    product.Price, "
					+ "    product.Stock, " 
					+ "    product.Sold, " 
					+ "    product.Image, " 
					+ "    product.Brand_Id, "
					+ "    brand.name as 'BrandName', " 
					+ "    brand.image as 'BrandImage', "
					+ "    product.Category_Id, " 
					+ "    category.name as 'CategoryName', "
					+ "    category.image as 'CategoryImage' "
					+ "FROM product JOIN brand ON product.Brand_Id = brand.Id "
					+ "JOIN category ON product.Category_Id = category.Id; ";

			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				int productId = myRs.getInt("Id");
				String productName = myRs.getString("Name");
				String productDesciption = myRs.getString("Desciption");
				double price = myRs.getDouble("Price");
				int stock = myRs.getInt("Stock");
				int sold = myRs.getInt("Sold");
				String image = myRs.getString("Image");
				int brandId = myRs.getInt("Brand_Id");
				String brandName = myRs.getString("BrandName");
				String brandImage = myRs.getString("BrandImage");
				int categoryId = myRs.getInt("Category_Id");
				String categoryName = myRs.getString("CategoryName");
				String categoryImage = myRs.getString("CategoryImage");

				Product product = new Product(productId, productName, productDesciption, price, stock, sold, image,
						new Brand(brandId, brandName, brandImage),
						new Category(categoryId, categoryName, categoryImage));
				products.add(product);
			}
			return products;
		} finally {
			close(myConn, myStmt, myRs);
		}
	}

	public Product getProductById(int productId) throws SQLException {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Product product = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "SELECT product.Name, " 
					+ "    product.Desciption, " 
					+ "    product.Price, "
					+ "    product.Stock, " 
					+ "    product.Sold, " 
					+ "    product.Image, " 
					+ "    product.Brand_Id, "
					+ "    brand.name as 'BrandName', " 
					+ "    brand.image as 'BrandImage', "
					+ "    product.Category_Id, " 
					+ "    category.name as 'CategoryName', "
					+ "    category.image as 'CategoryImage' "
					+ "FROM product JOIN brand ON product.Brand_Id = brand.Id "
					+ "JOIN category ON product.Category_Id = category.Id " + "WHERE product.ID = ? ;";

			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, productId);
			
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				String productName = myRs.getString("Name");
				String productDesciption = myRs.getString("Desciption");
				double price = myRs.getDouble("Price");
				int stock = myRs.getInt("Stock");
				int sold = myRs.getInt("Sold");
				String image = myRs.getString("Image");
				int brandId = myRs.getInt("Brand_Id");
				String brandName = myRs.getString("BrandName");
				String brandImage = myRs.getString("BrandImage");
				int categoryId = myRs.getInt("Category_Id");
				String categoryName = myRs.getString("CategoryName");
				String categoryImage = myRs.getString("CategoryImage");

				product = new Product(productId, productName, productDesciption, price, stock, sold, image,
						new Brand(brandId, brandName, brandImage),
						new Category(categoryId, categoryName, categoryImage));
			}
			return product;
		} finally {
			close(myConn, myStmt, myRs);
		}
	}
	
	public void addProduct(Product product) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "INSERT INTO Product (Name, Desciption, Price, Stock, Image, Brand_Id, Category_Id) "
					+ "VALUES( ?, ?, ?,?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, product.getName());
			myStmt.setString(2, product.getDescription());
			myStmt.setDouble(3, product.getPrice());
			myStmt.setInt(4, product.getStock());
			myStmt.setString(5, product.getImage());
			myStmt.setInt(6, product.getBrand().getId());
			myStmt.setInt(7, product.getCategory().getId());
			
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}
	}

	public void updateProduct(Product product) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "UPDATE product "
					+ "SET Name = ?,Desciption = ?,Price = ?, Stock = ?, Sold = ?, Image = ?, Brand_Id = ?, Category_Id = ? "
					+ "WHERE Id = ?";
			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, product.getName());
			myStmt.setString(2, product.getDescription());
			myStmt.setDouble(3, product.getPrice());
			myStmt.setInt(4, product.getStock());
			myStmt.setInt(5, product.getSold());
			myStmt.setString(6, product.getImage());
			myStmt.setInt(7, product.getBrand().getId());
			myStmt.setInt(8, product.getCategory().getId());

			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}
	}
	public void deleteProduct(int productId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			
			myConn = dataSource.getConnection();
			
			String sql = "DELETE from Product where Id=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, productId);
			
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}	
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
