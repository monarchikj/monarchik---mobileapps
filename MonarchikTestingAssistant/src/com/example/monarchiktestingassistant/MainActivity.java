package com.example.monarchiktestingassistant;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView lstStudents;
	private List <Student> students;
	private TestingCenter testingCenter;
	private Spinner spinnerSubject;
	private Spinner spinnerTestType;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		students = new ArrayList<Student>(0);
		testingCenter = new TestingCenter(this);
		
		lstStudents = (ListView)findViewById(R.id.lstStudents);
		
		populateStudentData();
		
		lstStudents.setOnItemClickListener(listViewListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void btnAddStudentClicked(View v){
		
		Intent addStudent = new Intent(this, EditStudentActivity.class);
		startActivity(addStudent);
		


		
		spinnerSubject = (Spinner)findViewById(R.id.spinnerSubject);
		spinnerTestType = (Spinner)findViewById(R.id.spinnerTestType);
		
		Toast.makeText(getApplicationContext(), "Complete all of the fields. Then press Go", 
		Toast.LENGTH_LONG).show();

				
	}
	
	public void populateStudentData(){
		List<String> studentStrings = new ArrayList<String> (0);
		
		students = testingCenter.getAllStudents();
		
		for (int i = 0; i < students.size(); i++){
			studentStrings.add(students.get(i).toString());
		}
		
		ArrayAdapter<String> arrayAdapter =
				new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, studentStrings);
		lstStudents.setAdapter(arrayAdapter);
		
	}
	
	private OnItemClickListener listViewListener = new OnItemClickListener(){
	
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			int testId = students.get(position).getTestId();
			
			goToEditStudent(testId);
			
		}
	};
	
	protected void goToEditStudent(int testId){
		Intent editStudent = new Intent(this, EditStudentActivity.class);
		
		editStudent.putExtra("com.example.monarchiktestingassistant", testId);
		startActivity(editStudent);
		
	}
}
