package com.neptune.service;

import java.sql.*;
import com.school.database.DatabaseConnect;
import com.school.model.LoginData;

public class LoginService {
private Connection connect = null;
private PreparedStatement stmt = null;
private ResultSet result = null;
LoginData details = new LoginData();

//process user login
	public LoginData login(String userid, String pass){
		int id = 0;
		String fname,lname,regno,dept,phoneno,email,username;
		try{
			connect = new DatabaseConnect().getConnection();
			String sql = "SELECT * FROM student_personal_details WHERE username=? AND password=?";
			   stmt = connect.prepareStatement(sql);
			      stmt.setString(1,userid);
			      stmt.setString(2,pass);
			      result = stmt.executeQuery();
			      if(result.next()){
			    	  System.out.println("ok");
			    	  id = result.getInt("id");
			    	  fname = result.getString("fname");
			    	  lname = result.getString("lname");
			    	  regno = result.getString("reg_no");
			    	  dept = result.getString("dept");
			    	  phoneno = result.getString("phone_no");
			    	  email = result.getString("email");
			    	  username = result.getString("username");  
			    	  
			    	  details.setRegId(id);
			    	  details.setfName(fname);
			    	  details.setlName(lname);
			    	  details.setRegNo(regno);
			    	  details.setDepT(dept);
			    	  details.setPhoneNo(phoneno);
			    	  details.seteMail(email);
			    	  details.setUserName(username);
			    	  details.setSortMessage("Successful");
			      }
			      else{
			    	  details.setRegId(id);
			    	  details.setSortMessage("Login failed. Invalid credentials");
			      }
			      
		}
		catch(Exception e){
			   details.setSortMessage("Could not connect to Database.");
			   e.printStackTrace();
		}
		return details;
	}
//process registration of new users
public LoginData createUser(String fName, String Lname, String regNo, String depT, String phoneN, String email, String userN, String passW){
	try{
		connect = new DatabaseConnect().getConnection();
		String sql = "INSERT INTO student_personal_details(fname,lname,reg_no,dept,phone_no,email,username,password) VALUES(?,?,?,?,?,?,?,?)";
		stmt = connect.prepareStatement(sql);
		stmt.setString(1,fName);
		stmt.setString(2,Lname);
		stmt.setString(3,regNo);
		stmt.setString(4,depT);
		stmt.setString(5, phoneN);
		stmt.setString(6,email);
		stmt.setString(7,userN);
		stmt.setString(8,passW);
		if(stmt.executeUpdate()>0){
			details.setSortMessage("Successful");
		}
		else{
			details.setSortMessage("Failed");
		}
		
	}
	catch(Exception e){
		details.setSortMessage("Could not connect to Database.");
		   e.printStackTrace();
	}
	return details;
}
	
}
