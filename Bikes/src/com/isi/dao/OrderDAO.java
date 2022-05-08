package com.isi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.isi.data.Order;
import com.isi.data.Product;

public class OrderDAO {
	private DataSource dataSource;
	private ProductDAO productDAO;

	public OrderDAO(DataSource dataSource) {
		this.dataSource = dataSource;
		this.productDAO = new ProductDAO(dataSource);
	}
	
	public void addOrder(Order order) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = 	"	INSERT INTO bikes.orders "
						+ 	"	(Product_Id, Size, Subtotal, QST, GST, Total) "
						+ 	"	VALUES(?,?,?,?,?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, order.getProduct().getId());
			myStmt.setString(2, order.getSize());
			myStmt.setDouble(3, order.getSubtotal());
			myStmt.setDouble(4, order.getQst());
			myStmt.setDouble(5, order.getGst());
			myStmt.setDouble(6, order.getTotal());
			
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}
	}
	public List<Order> getOrdersList() throws SQLException {

		List<Order> orders = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "SELECT orders.Id, "
					+ "    orders.Product_Id, "
					+ "    orders.Size, "
					+ "    orders.Subtotal, "
					+ "    orders.QST, "
					+ "    orders.GST, "
					+ "    orders.Total "
					+ "FROM bikes.orders  "
					+ "JOIN bikes.product ON orders.Product_Id = product.Id";

			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				int productId = myRs.getInt("Product_Id");
				String size = myRs.getString("Size");
				double subtotal = myRs.getDouble("Subtotal");
				double qst = myRs.getDouble("QST");
				double gst = myRs.getDouble("GST");
				double total = myRs.getDouble("Total");
				
				Product product = productDAO.getProductById(productId);
				Order order =  new Order(productId, product, size, subtotal, qst, gst, total);
				orders.add(order);
			}
			return orders;
		} finally {
			close(myConn, myStmt, myRs);
		}
	}
	
	public int getTotalSales() throws SQLException {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int totalSales = -1;

		try {
			myConn = dataSource.getConnection();

			String sql = "SELECT SUM(orders.Total) AS 'TotalSales' "
					+ " FROM bikes.orders ";

			myStmt = myConn.prepareStatement(sql);

			myRs = myStmt.executeQuery();
			if (myRs.next()) {
				totalSales = myRs.getInt("TotalSales");
			}
			return totalSales;
		} finally {
			close(myConn, myStmt, myRs);
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
