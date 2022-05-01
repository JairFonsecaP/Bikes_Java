package com.isi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import com.isi.data.Order;

public class OrderDAO {
	private DataSource dataSource;

	public OrderDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void addOrder(Order order) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "INSERT INTO orders (Subtotal,QST,GST,Total) "
					+ "VALUES(?,?,?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setDouble(1, order.getSubtotal());
			myStmt.setDouble(2, order.getQst());
			myStmt.setDouble(3, order.getGst());
			myStmt.setDouble(4, order.getTotal());
			
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
