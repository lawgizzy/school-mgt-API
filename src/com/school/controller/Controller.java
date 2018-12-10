package com.school.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.neptune.service.LoginService;
import com.neptune.service.UserService;
import com.school.model.LoginData;
import com.school.model.UserData;

@Path("/api")
public class Controller {
	UserData data = new UserData();

	//Defining the webservice method for authenticate users trying to log in
	@POST	
	@Path("/user-login")
	@Consumes(MediaType.APPLICATION_JSON)
	public String defineUserLogin(@QueryParam("username") String userN, @QueryParam("password") String passW){
		LoginData val = new LoginService().login(userN, passW);
		String jsonval = new Gson().toJson(val);
		 return jsonval;
	}

	//Defining the webservice method for creating users
	@POST	
	@Path("/user-registration")
	@Consumes(MediaType.APPLICATION_JSON)
	public String defineUserReg(@QueryParam("fname") String fName, @QueryParam("lname") String lName, @QueryParam("reg_no") String regN, 
			@QueryParam("dept") String depT, @QueryParam("phone_no") String phoneN, @QueryParam("email") String eMail, @QueryParam("username") String userN, @QueryParam("password") String passW){

		LoginData val = new LoginService().createUser(fName, lName, regN, depT, phoneN, eMail, userN, passW);
		String jsonval = new Gson().toJson(val);
		 return jsonval;
	}
	
	// Generating the webservice method for course registration
	@POST
	@Path("/course-registration")
	@Consumes(MediaType.APPLICATION_JSON)
	public String courseReg(@QueryParam("stud_reg_id") int studId, @QueryParam("course_code") String cCode,
			@QueryParam("course_title") String cTitle, @QueryParam("course_unit") int cUnit,
			@QueryParam("lecturer_name") String lectN) {
		data = new UserService().courseReg(studId, cCode, cTitle, cUnit, lectN);
		return new Gson().toJson(data);
	}

	// service for password reset
	@POST
	@Path("/password-reset")
	@Consumes(MediaType.APPLICATION_JSON)
	public String passReset(@QueryParam("username") String userN, @QueryParam("password") String passW,
			@QueryParam("confirmPassword") String confamPass) {
		data = new UserService().manageAcct(userN, passW, confamPass);
		return new Gson().toJson(data);
	}

	// service for sending messages to admin as feedback
	@POST
	@Path("/feedback")
	@Consumes(MediaType.APPLICATION_JSON)
	public String feedback(@QueryParam("username") String userN, @QueryParam("subject") String subj,
			@QueryParam("message") String msg) {
		data = new UserService().feedback(userN, subj, msg);
		return new Gson().toJson(data);
	}

	// barchart update that shows the total courses registered over a period of five years
	@POST
	@Path("/insight")
	@Consumes(MediaType.APPLICATION_JSON)
	public String chartUpdate(@QueryParam("regId") int userId) {
		data = new UserService().chartDetails(userId);
		return new Gson().toJson(data);

	}

}
