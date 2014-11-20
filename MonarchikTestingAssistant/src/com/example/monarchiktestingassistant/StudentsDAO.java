package com.example.monarchiktestingassistant;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class StudentsDAO {
	private SQLiteDatabase database;
	private StudentsSQLiteHelper dbHelper;
	private String [] allColumns = {
				StudentsSQLiteHelper.COLUMN_COMPLETED,
				StudentsSQLiteHelper.COLUMN_FIRSTNAME,
				StudentsSQLiteHelper.COLUMN_LASTNAME,
				StudentsSQLiteHelper.COLUMN_TEACHER,
				StudentsSQLiteHelper.COLUMN_STUDENTID,
				StudentsSQLiteHelper.COLUMN_TIMEIN,
				StudentsSQLiteHelper.COLUMN_TIMEOUT,
				StudentsSQLiteHelper.COLUMN_TESTTYPE,
				StudentsSQLiteHelper.COLUMN_TESTSUBJECT,
				StudentsSQLiteHelper.COLUMN_EXTRANOTES,
				StudentsSQLiteHelper.COLUMN_TESTID};
		
	//Constructor
	public StudentsDAO(Context context){
		dbHelper = new StudentsSQLiteHelper(context);
	}
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	public void close(){
		dbHelper.close();
	}
public Student createStudent(Student s){
	ContentValues values = new ContentValues();
	
	int done = 0;
	if (s.getCompleted()){
		done = 1;
	}
	
	//Place s data into values object

	values.put(StudentsSQLiteHelper.COLUMN_EXTRANOTES, s.getExtraNotes());
	values.put(StudentsSQLiteHelper.COLUMN_FIRSTNAME, s.getFirstName());
	values.put(StudentsSQLiteHelper.COLUMN_LASTNAME, s.getLastName());
	values.put(StudentsSQLiteHelper.COLUMN_STUDENTID, s.getStudentId());
	values.put(StudentsSQLiteHelper.COLUMN_TEACHER, s.getTeacher());
	values.put(StudentsSQLiteHelper.COLUMN_TESTSUBJECT, s.getTestSubject());
	values.put(StudentsSQLiteHelper.COLUMN_TESTTYPE, s.getTestType());
	values.put(StudentsSQLiteHelper.COLUMN_TIMEIN, s.getTimeIn());
	values.put(StudentsSQLiteHelper.COLUMN_TIMEOUT, s.getTimeOut());
	values.put(StudentsSQLiteHelper.COLUMN_COMPLETED, done);
//	values.put(StudentsSQLiteHelper.COLUMN_TESTID, s.getTestId());
	
	long insertId = database.insert(StudentsSQLiteHelper.TABLE_STUDENTS, null, values);
	
	Cursor cursor = database.query(StudentsSQLiteHelper.TABLE_STUDENTS, allColumns,
			StudentsSQLiteHelper.COLUMN_TESTID + " = " + insertId, null, null, null, null);
	cursor.moveToLast();
	Student newStudent = cursorToStudent(cursor);
	cursor.close();
	
	return newStudent;
}

public void deleteStudent(Student s){
	int id = s.getTestId();
	database.delete(StudentsSQLiteHelper.TABLE_STUDENTS, 
			StudentsSQLiteHelper.COLUMN_TESTID + " = " + id, null);
}

public void editStudent(Student s){
	ContentValues values = new ContentValues();
	int id = s.getTestId();
	
	int done = 0;
	if (s.getCompleted()){
		done = 1;
	}
	//Check here
	values.put(StudentsSQLiteHelper.COLUMN_COMPLETED, done);
	values.put(StudentsSQLiteHelper.COLUMN_FIRSTNAME, s.getFirstName());
	values.put(StudentsSQLiteHelper.COLUMN_LASTNAME, s.getLastName());
	values.put(StudentsSQLiteHelper.COLUMN_TEACHER, s.getTeacher());
	values.put(StudentsSQLiteHelper.COLUMN_STUDENTID, s.getStudentId());
	values.put(StudentsSQLiteHelper.COLUMN_TIMEIN, s.getTimeIn());
	values.put(StudentsSQLiteHelper.COLUMN_TIMEOUT, s.getTimeOut());
	values.put(StudentsSQLiteHelper.COLUMN_TESTTYPE, s.getTestType());
	values.put(StudentsSQLiteHelper.COLUMN_TESTSUBJECT, s.getTestSubject());
	values.put(StudentsSQLiteHelper.COLUMN_EXTRANOTES, s.getExtraNotes());
	values.put(StudentsSQLiteHelper.COLUMN_TESTID, s.getTestId());
	
	database.update(StudentsSQLiteHelper.TABLE_STUDENTS, values,
			StudentsSQLiteHelper.COLUMN_TESTID + " = " + id, null);
	
}

public List <Student> getAllStudents(){
	List <Student> studentList = new ArrayList<Student>(0);
	Cursor cursor = database.query(StudentsSQLiteHelper.TABLE_STUDENTS, allColumns, null, null, null, null, null);
	
	cursor.moveToFirst();
	while(!cursor.isAfterLast()){
		Student student = cursorToStudent(cursor);
		studentList.add(student);
		cursor.moveToNext();
	} //End while
	
	return studentList;
}	//End getAllStudents

private Student cursorToStudent(Cursor cursor){
	boolean complete = cursor.getInt(0)== 1;
	String firstName = cursor.getString(1);
	String lastName = cursor.getString(2);
	String teacher = cursor.getString(3);
	int studentId = cursor.getInt(4);
	int timeIn = cursor.getInt(5);
	String timeOut = cursor.getString(6);
	String testType = cursor.getString(7);
	String testSubject = cursor.getString(8);
	String extraNotes = cursor.getString(9);
	int testId = cursor.getInt(10);

	
	
//int testId = 2;
	//TODO: Check here
	Student s = new Student(complete, firstName, lastName, teacher, studentId, timeIn, timeOut, testType, testSubject, extraNotes, testId);
	

	return s;
}
	
public Student getStudentById(int id){
	Cursor cursor = database.query(StudentsSQLiteHelper.TABLE_STUDENTS, allColumns, StudentsSQLiteHelper.COLUMN_TESTID + " = " + id, null, null, null, null);
//	return (cursor.moveToFirst()) ? cursorToStudent(cursor):null;
	cursor.moveToLast();
	return cursorToStudent(cursor);
	
}


} // End StudentsDAO

