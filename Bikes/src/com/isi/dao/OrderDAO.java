package com.isi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.catalina.ant.jmx.JMXAccessorGetTask;

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
