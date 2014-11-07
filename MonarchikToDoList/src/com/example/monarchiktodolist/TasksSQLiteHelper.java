package com.example.monarchiktodolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TasksSQLiteHelper extends SQLiteOpenHelper{
//Define the Table and Columns
//Static doesnt change and we can access the value directly.
//Defining constants to create table
//Create a database and this table is called tasks.
	
	public static final String TABLE_TASKS = "tasks";
	
	//Columns, instructions for how to build. Everything is a string
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_PRIORITY = "priority";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_TASK = "task";
	public static final String COLUMN_COMPLETED = "completed";
	//Name
	public static final String DATABASE_NAME = "task.db";
	//Version
	public static final int DATABASE_VERSION = 1;
	
	
	//Database creation SQL statement
	//Do all of the instructions system
	
	private static final String DATABASE_CREATE = 
			"create table "
			+ TABLE_TASKS + "("
			+ COLUMN_ID + "integer primary key autoincrement, "
			+ COLUMN_PRIORITY + " integer not null, "
			+ COLUMN_DATE + " text not null, "
			+ COLUMN_TASK + " text not null"
			+ COLUMN_COMPLETED + " integer not null);";
	
	
	// Create table TABLE _TASKS (COLUMN_ID xxxxxxx, COLUMN_PRIORITY
	// autoincrement: automatically assigns id #
	// Can not make these null, something must be there.
	// Here we describe what is going to be in the column. 
	
	
	//Constructor that puts it together and handles it to the rest of the app. 
	public TasksSQLiteHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		
		//Call this in the DAO to create it.
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_TASKS);
		onCreate(db);
		
	}
	
	
	
}
