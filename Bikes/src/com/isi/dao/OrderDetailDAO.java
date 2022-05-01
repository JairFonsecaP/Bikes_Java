package com.isi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import com.isi.data.OrderDetail;

public class OrderDetailDAO {
	private DataSource dataSource;

	public OrderDetailDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void addOrderDetail(OrderDetail orderDetail) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "INSERT INTO bikes.product_has_orders "
					+ "(Product_Id, Orders_Id, Quantity, Price_Unit) "
					+ "VALUES(?,?,?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, orderDetail.getProduct().getId());
			myStmt.setInt(2, orderDetail.getOrder().getId());
			myStmt.setInt(3, orderDetail.getQuantity());
			myStmt.setDouble(4, orderDetail.getUnitPrice());
			
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
