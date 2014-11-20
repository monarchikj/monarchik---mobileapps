package com.example.monarchiktestingassistant;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//Test #1:
	// 11/12/2014 4:42 PM
	// Tested basic functionality of app without active sliders.
	//
public class StudentsSQLiteHelper extends SQLiteOpenHelper{
	//Define Table and Columns
		public static final String TABLE_STUDENTS = "students";
		
		public static final String COLUMN_COMPLETED = "completed";
		public static final String COLUMN_FIRSTNAME = "firstname";
		public static final String COLUMN_LASTNAME = "lastname";
		public static final String COLUMN_TEACHER = "teacher";
		public static final String COLUMN_STUDENTID = "studentId";
		public static final String COLUMN_TIMEIN = "timeIn";
		public static final String COLUMN_TIMEOUT = "timeOut";
		public static final String COLUMN_TESTTYPE = "testType";
		public static final String COLUMN_TESTSUBJECT = "testSubject";
		public static final String COLUMN_EXTRANOTES = "extraNotes";
		public static final String COLUMN_TESTID = "testId";
		
		
		public static final String DATABASE_NAME = "student.db";
		public static final int DATABASE_VERSION = 1;
		
		//Create Database
		
		private static final String DATABASE_CREATE = "create table "
				+ TABLE_STUDENTS + "("
				+ COLUMN_COMPLETED + " integer not null, "
				+ COLUMN_FIRSTNAME + " text not null, "
				+ COLUMN_LASTNAME + " text not null, "
				+ COLUMN_TEACHER + " text not null, "
				+ COLUMN_STUDENTID + " integer not null, "
				+ COLUMN_TIMEIN + " integer not null, "
				+ COLUMN_TIMEOUT + " integer not null, "
				+ COLUMN_TESTTYPE + " text not null, "
				+ COLUMN_TESTSUBJECT + " text not null, "
				+ COLUMN_EXTRANOTES + " text not null, "
				+ COLUMN_TESTID + " integer primary key autoincrement);";
		
		//check line above
//Constructor
	public StudentsSQLiteHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_STUDENTS);
		onCreate(db);
		
	}
 
}// End SQLiteHelper
