package com.example.monarchiktestingassistant;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class TestingCenter {
	
	private List <Student> testingCenter;
	private StudentsDAO studentsDataSource;
	
	//Program DAO
	public TestingCenter(Context c){
		testingCenter = new ArrayList<Student>(0);
		studentsDataSource = new StudentsDAO(c);
		studentsDataSource.open();
	}
	
	public Student getStudent(int testId){
		return studentsDataSource.getStudentById(testId);
	}
	
	public Student createStudent(Student s){
		return studentsDataSource.createStudent(s);
	}
	//Check this line
	public  void editStudent(Student s){
		  studentsDataSource.editStudent(s);
	}
	public void deleteStudent(Student s){
		studentsDataSource.deleteStudent(s);
	}
	
	public List<Student> getAllStudents(){
		testingCenter = studentsDataSource.getAllStudents();
		return testingCenter;
	}
}

