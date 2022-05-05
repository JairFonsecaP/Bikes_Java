package com.isi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.isi.data.Admin;
import com.isi.util.PasswordEncrypt;

public class AdminDAO {
	private DataSource dataSource;

	public AdminDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Admin getAdminByUsername(String adminUsername) throws SQLException {

		Admin admin = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "SELECT admins.Id, "
					+ "    admins.Name, "
					+ "    admins.Password, "
					+ "    admins.Salt "
					+ "FROM bikes.admins "
					+ "WHERE Username = ? ;";
					
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, adminUsername);
			
			
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				int adminId = myRs.getInt("Id");
				String name = myRs.getString("Name");
				String password = myRs.getString("Password");
				String salt = myRs.getString("Salt");

				admin = new Admin(adminId, name, adminUsername, password, salt);
			}
			return admin;
		} finally {
			close(myConn, myStmt, myRs);
		}
	}
	
	public void addAdmin(Admin admin) throws Exception {
		
		admin = encryptPassword(admin); 
		System.out.println("paso");
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "INSERT INTO admins (Name,Username,Password,Salt) "
					+ "VALUES(?,?,?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, admin.getName());
			myStmt.setString(2, admin.getUsername());
			myStmt.setString(3, admin.getPassword());
			myStmt.setString(4, admin.getSalt());
			
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}
	}

	private Admin encryptPassword(Admin admin) {
		String saltvalue = PasswordEncrypt.getSaltvalue(30);
		admin.setPassword(PasswordEncrypt.generateSecurePassword(admin.getPassword(), saltvalue));
		admin.setSalt(saltvalue);
		System.out.println(admin.getPassword());
		System.out.println(admin.getSalt());
		return admin;
	}
	
	public boolean authenticateAdmin(String username, String password) throws SQLException {
		
		Admin admin = getAdminByUsername(username);
		if (admin == null)
			return false;
		else 
			return PasswordEncrypt.verifyUserPassword(password,admin.getPassword(),admin.getSalt());  
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
