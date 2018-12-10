package com.school.database;

import java.sql.*;

public class DatabaseConnect {
private Connection connection = null;

public Connection getConnection(){
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost/school_mgt_system","root","");
		return connection;
	}
	catch(Exception e){
		return null;
	}
}
}

