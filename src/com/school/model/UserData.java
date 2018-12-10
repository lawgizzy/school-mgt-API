package com.school.model;

public class UserData {
	// POJO CLASS
	private int regId;
	private String feedSubject;
	private String year[] = new String[5];

	public String[] getYear() {
		return year;
	}

	public void setYear(String[] year) {
		this.year = year;
	}

	public int[] getTotalCourses() {
		return totalCourses;
	}

	public void setTotalCourses(int[] totalCourses) {
		this.totalCourses = totalCourses;
	}

	private int totalCourses[] = new int[5];

	public String getFeedSubject() {
		return feedSubject;
	}

	public void setFeedSubject(String feedSubject) {
		this.feedSubject = feedSubject;
	}

	public String getFeedMessage() {
		return feedMessage;
	}

	public void setFeedMessage(String feedMessage) {
		this.feedMessage = feedMessage;
	}

	private String feedMessage;

	public int getRegId() {
		return regId;
	}

	public void setRegId(int regId) {
		this.regId = regId;
	}

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public String getcTitle() {
		return cTitle;
	}

	public void setcTitle(String cTitle) {
		this.cTitle = cTitle;
	}

	public int getcUnit() {
		return cUnit;
	}

	public void setcUnit(int cUnit) {
		this.cUnit = cUnit;
	}

	public String getLectName() {
		return lectName;
	}

	public void setLectName(String lectName) {
		this.lectName = lectName;
	}

	public String getPassW() {
		return passW;
	}

	public void setPassW(String passW) {
		this.passW = passW;
	}

	public String getConfamPass() {
		return confamPass;
	}

	public void setConfamPass(String confamPass) {
		this.confamPass = confamPass;
	}

	public String getSortMessage() {
		return sortMessage;
	}

	public void setSortMessage(String sortMessage) {
		this.sortMessage = sortMessage;
	}

	private String cCode;
	private String cTitle;
	private int cUnit;
	private String lectName;
	private String passW;
	private String confamPass;
	private String sortMessage;

}
