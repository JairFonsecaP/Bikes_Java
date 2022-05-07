package com.isi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.isi.data.Brand;


public class BrandDAO {
	private DataSource dataSource;

	public BrandDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public List<Brand> getBrands() throws SQLException {

		List<Brand> brands = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "select * from brand order by Id";

			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {

				int id = myRs.getInt("Id");
				String name = myRs.getString("Name");
				String image = myRs.getString("Image");

				Brand brand = new Brand(id, name, image);

				brands.add(brand);
			}
			return brands;
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
