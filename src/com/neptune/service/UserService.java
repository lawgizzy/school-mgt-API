package com.neptune.service;

import java.sql.*;

import com.school.database.DatabaseConnect;
import com.school.model.UserData;

import java.util.*;

public class UserService {
	private Connection connect = null;
	private PreparedStatement stmt = null;
	private ResultSet result = null;
	private UserData details = new UserData();

	//process registration of new courses for users
	public UserData courseReg(int regId, String cCode, String cTitle, int cUnit, String lectName) {
		try {
			connect = new DatabaseConnect().getConnection();
			String sql = "INSERT INTO course_reg(stud_reg_id,course_code,course_title,course_unit,lecturer_name) VALUES(?,?,?,?,?)";
			stmt = connect.prepareStatement(sql);
			stmt.setInt(1, regId);
			stmt.setString(2, cCode);
			stmt.setString(3, cTitle);
			stmt.setInt(4, cUnit);
			stmt.setString(5, lectName);
			if (stmt.executeUpdate() > 0) {
				details.setRegId(regId);
				details.setcCode(cCode);
				details.setcTitle(cTitle);
				details.setcUnit(cUnit);
				details.setLectName(lectName);
				details.setSortMessage("Successful");

			} else {
				details.setSortMessage("Registration failed. Recheck the Student registration ID and try again.");
			}
		} catch (Exception e) {
			details.setSortMessage("Could not connect to Database.");
			e.printStackTrace();
		}

		return details;
	}

	//manage account for users
	public UserData manageAcct(String userId, String passW, String confamPass) {
		// check if both entered passwords on the textfields match
		if (passW.equals(confamPass)) {
			try {
				connect = new DatabaseConnect().getConnection();
				// checking if the user exists in the database
				String sql = "SELECT * FROM student_personal_details WHERE username=?";
				stmt = connect.prepareStatement(sql);
				stmt.setString(1, userId);
				result = stmt.executeQuery();
				if (result.next()) {
					// retrieving the user id from the database after successful
					// check
					int rid = result.getInt("id");

					// updating the user password
					String sql2 = "UPDATE student_personal_details SET password =? WHERE id=?";
					stmt = connect.prepareStatement(sql2);
					stmt.setString(1, passW);
					stmt.setInt(2, rid);
					if (stmt.executeUpdate() > 0) {
						details.setSortMessage("Updated");
					} else {
						details.setSortMessage("Oops! Network error occurred!");
					}
				} else {
					details.setSortMessage("No record found. Try again.");
				}
			} catch (Exception e) {
				details.setSortMessage("Could not connect to Database.");
				e.printStackTrace();
			}
		} else {
			details.setSortMessage("Passwords do not match!");
		}

		return details;
	}

	//handle feedback
	public UserData feedback(String username, String subject, String message) {
		try {
			connect = new DatabaseConnect().getConnection();
			String sql = "SELECT id FROM student_personal_details WHERE username=?";
			stmt = connect.prepareStatement(sql);
			stmt.setString(1, username);
			if (stmt.execute()) {
				result = stmt.executeQuery();
				if (result.next()) {
					int userId = result.getInt("id");
					String sql2 = "INSERT INTO feedback (user_id,subject,message) VALUES(?,?,?)";
					stmt = connect.prepareStatement(sql2);
					stmt.setInt(1, userId);
					stmt.setString(2, subject);
					stmt.setString(3, message);
					if (stmt.executeUpdate() > 0) {
						details.setRegId(userId);
						details.setFeedSubject(subject);
						details.setFeedMessage(message);
						details.setSortMessage("Sent");
					} else {
						details.setSortMessage("Network error occurred");
					}
				} else {
					details.setSortMessage("No record found.");
				}
			} else {
				details.setSortMessage("Network Error occurred.");
			}
		} catch (Exception e) {
			details.setSortMessage("Database connection error");
		}
		return details;
	}

	//process updating of records for clients which is visible on a bar chart on the front-end
	public UserData chartDetails(int regId) {
		int year[] = new int[5];
		int totCourses[] = new int[5];
		String yearStr[] = new String[year.length];

		try {
			connect = new DatabaseConnect().getConnection();

			// getting the year of registration of the user which would be used
			// as the base year
			String sql = "SELECT YEAR(date_reg) AS 'year' FROM `student_personal_details` WHERE id = ?";
			stmt = connect.prepareStatement(sql);
			stmt.setInt(1, regId);
			if (stmt.execute()) {
				result = stmt.executeQuery();
				if (result.next()) {
					year[0] = result.getInt("year");
				}
			}
			for (int i = 1; i < year.length; i++) {
				year[i] = year[0] + i;
			}

			// getting all courses registered from the base year until five
			// years time
			int j = 0;
			String sql2 = "SELECT YEAR(date_reg) AS 'year', COUNT(date_reg) AS 'courses_registered' FROM `course_reg` WHERE stud_reg_id = ? GROUP BY YEAR(date_reg)";
			stmt = connect.prepareStatement(sql2);
			stmt.setInt(1, regId);
			if (stmt.execute()) {
				result = stmt.executeQuery();
				while (result.next()) {
					int yr = result.getInt("year");
					int no_courses = result.getInt("courses_registered");
					if (year[j] == yr) {
						totCourses[j] = no_courses;
					}

					j++;
				}

				for (int i = 0; i < year.length; i++) {
					yearStr[i] = String.valueOf(year[i]);
				}
				details.setRegId(regId);
				details.setYear(yearStr);
				details.setTotalCourses(totCourses);
			} else {
				details.setSortMessage("Network Error Occurred.");
				System.out.println("Network Error Occurred.");
			}
		} catch (Exception e) {
			details.setSortMessage("Could not connect to database");

		}
		return details;
	}

}
