package com.isi.db;

import javax.sql.DataSource;

public class BikesDbUtil 
{
private DataSource dataSource;
	
	public BikesDbUtil(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

}
