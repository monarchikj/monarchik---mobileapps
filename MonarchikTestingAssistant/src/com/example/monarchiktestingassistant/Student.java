package com.example.monarchiktestingassistant;

public class Student {
	//Fields
	private boolean isCompleted;
	private String firstname;
	private String lastname;
	private String teacher;
	private int studentId;
	private int timeIn;
	//Change
	private String timeOut;
	private String extraNotes;
	private String testType;
	private String testSubject;
	private int testId;
	//Constructor
public Student(){
	
}
	public Student(boolean c, String fn, String ln, String t, int id, int in, String out, String type, String subject,String notes,  int tid){

		isCompleted = c;
		firstname = fn;
		lastname = ln;
		teacher = t;
		studentId = id;
		timeIn = in;
		timeOut = out;
		testType = type;
		testSubject = subject;
		extraNotes = notes;
		testId = tid;
		
	}

	public boolean getCompleted(){
		return isCompleted;
	}
	public String getFirstName(){
		return firstname;
	}
	public String getLastName(){
		return lastname;
	}
	public String getTeacher(){
		return teacher;
	}
	public int getStudentId(){
		return studentId;
	}
	public int getTimeIn(){
		return timeIn;
	}
	public String getTimeOut(){
		return timeOut;
	}
	public String getTestType(){
		return testType;
	}
	public String getTestSubject(){
		return testSubject;
	}
	public String getExtraNotes(){
		return extraNotes;
	}

	public int getTestId(){
		return testId;
	}
	
	
	//Getters and Setters
	public void setCompleted(boolean p){
		isCompleted = p;
	}
	public void setFirstName(String fn){
		firstname = fn;
	}
	public void setLastName(String ln){
		lastname = ln;
	}
	public void setTeacher(String t){
		teacher = t;
	}
	public void setStudentId(int id){
		studentId = id;
	}
	public void setTimeIn(int ti){
		timeIn = ti;
	}
	public void setTimeOut(String to){
		timeOut = to;
	}
	public void setTestType(String type){
		testType = type;
	}
	public void setTestSubject(String subject){
		testSubject = subject;
	}
	public void setExtraNotes(String en){
		extraNotes = en;
	}
	public void setTestId(int ti){
		testId = ti;
	}
	
public String toString(){
	String result = "";
	
	result += firstname + " " + lastname + " " + String.valueOf(testId) + "  " + timeIn;
	return result;
}
		

}  //End Student Class
