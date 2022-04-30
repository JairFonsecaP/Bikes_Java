package com.isi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.isi.data.Category;

public class CategoryDAO {
	private DataSource dataSource;

	public CategoryDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Category> getAllCategoriesList() throws SQLException {

		List<Category> categories = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "SELECT Id, Name, Image "
					+ "FROM category ";

			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				int id = myRs.getInt("Id");
				String name = myRs.getString("Name");
				String image = myRs.getString("Image");

				Category category = new Category(id, name, image);
				categories.add(category);
			}
			return categories;
		} finally {
			close(myConn, myStmt, myRs);
		}
	}
	public Category getCategoryById(int categoryId) throws SQLException {

		Category category = null;
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "SELECT Name, Image "
					+ "FROM category "
					+ "WHERE Id = " + categoryId;

			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				String name = myRs.getString("Name");
				String image = myRs.getString("Image");

				category = new Category(categoryId, name, image);
			}
			return category;
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
