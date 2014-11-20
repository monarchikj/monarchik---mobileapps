package com.example.monarchiktestingassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditStudentActivity extends Activity {
	
	private TestingCenter testingCenter;
	
	private TextView txtTestId;
	private EditText edtFirstName;
	private EditText edtLastName;
	private EditText edtStudentId;
	private EditText edtTeacher;
	private Spinner spinnerSubject;
	private Spinner spinnerTestType;
	private EditText edtTimeIn;
	private EditText edtTimeOut;
	private EditText edtExtraNotes;
	private Button btnAddStudent;
	private Button btnEditStudent;
	private Button btnDeleteStudent;
	private Button btnMainMenu;
	private CheckBox chkCompleted;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_student);
		//Bind onCreate
		txtTestId = (TextView)findViewById(R.id.txtTestId);
		edtFirstName = (EditText)findViewById(R.id.edtFirstname);
		edtLastName = (EditText)findViewById(R.id.edtLastName);
		edtStudentId = (EditText)findViewById(R.id.edtStudentId);
		edtTeacher = (EditText)findViewById(R.id.edtTeacher);
		edtTimeIn = (EditText)findViewById(R.id.edtTimeIn);
		edtTimeOut = (EditText)findViewById(R.id.editTimeOut);
		spinnerSubject = (Spinner)findViewById(R.id.spinnerSubject);
		spinnerTestType = (Spinner)findViewById(R.id.spinnerTestType);
		edtExtraNotes = (EditText)findViewById(R.id.edtExtraNotes);
		btnAddStudent = (Button)findViewById(R.id.btnAddStudent);
		btnDeleteStudent = (Button)findViewById(R.id.btnDeleteStudent);
		btnEditStudent = (Button)findViewById(R.id.btnEditStudent);
		btnMainMenu = (Button)findViewById(R.id.btnMainMenu);
		chkCompleted = (CheckBox)findViewById(R.id.chkCompleted);
		testingCenter = new TestingCenter(this);
		
		Intent intent = getIntent();
		int testId = intent.getIntExtra("com.example.monarchiktestingassistant", 0);
		
		//Test Type Spinner
		ArrayAdapter<CharSequence> typeadapter = 
				ArrayAdapter.createFromResource(this, R.array.type_array, 
						android.R.layout.simple_spinner_dropdown_item);
		typeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerTestType.setAdapter(typeadapter);
		
		//TestSubject
		ArrayAdapter<CharSequence> subjectadapter = 
				ArrayAdapter.createFromResource(this, R.array.subject_array, android.R.layout.simple_spinner_dropdown_item);
		subjectadapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spinnerSubject.setAdapter(subjectadapter);
		
		if (testId != 0){
			populateStudentData(testId);
		}
			

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_student, menu);
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
		Toast.makeText(getApplicationContext(), "Welcome! Please take a seat.", 
		Toast.LENGTH_LONG).show();
		addStudent();
	}
	
	public void btnEditStudentClicked(View v){
		editStudentData();
	}
	public void btnDeleteStudentClicked(View v){
		deleteStudent();
	}
	public void btnMainMenuClicked(View v){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	private void populateStudentData(int testId){
		Student s = testingCenter.getStudent(testId);
		
			chkCompleted.setChecked(s.getCompleted());
			edtFirstName.setText(String.valueOf(s.getFirstName()));
			edtLastName.setText(String.valueOf(s.getLastName()));
			edtTeacher.setText(String.valueOf(s.getTeacher()));
			edtStudentId.setText(String.valueOf(s.getStudentId()));
			edtTimeIn.setText(String.valueOf(s.getTimeIn()));
			edtTimeOut.setText(String.valueOf(s.getTimeOut()));
			edtExtraNotes.setText(String.valueOf(s.getExtraNotes()));
			txtTestId.setText(String.valueOf(s.getTestId()));

			
			int subject = 0;
			int type = 0;
			
			if (s.getTestSubject().equals("English")){
				subject = 1;
				}
			if (s.getTestSubject().equals("Math")){
				subject = 0;
			}
			if (s.getTestSubject().equals("Science")){
				subject = 2;
			}
			if (s.getTestSubject().equals("Language")){
				subject = 3;
			}
			if (s.getTestSubject().equals("Religion")){
				subject = 4;
			}
			if (s.getTestSubject().equals("History")){
				subject = 5;
			}
			if (s.getTestSubject().equals("Other")){
				subject = 6;
			}
			
			if (s.getTestType().equals("Test")){
				type = 0;
			}
			if (s.getTestType().equals("Quiz")){
				type = 1;
			}
			if (s.getTestType().equals("Essay")){
				type = 2;
			}
			if (s.getTestType().equals("Exam")){
				type = 3;
			}
			if (s.getTestType().equals("Other")){
				type = 4;
			}
			spinnerTestType.setSelection(type);
			spinnerSubject.setSelection(subject);

		
	}
	
	private void editStudentData(){
		if (txtTestId.getText().toString().length() > 0){
			int testId = Integer.valueOf(txtTestId.getText().toString());
			Student s = testingCenter.getStudent(testId);
			

			s.setCompleted(chkCompleted.isChecked());
			s.setFirstName(edtFirstName.getText().toString());
			s.setLastName(edtLastName.getText().toString());
			s.setTeacher(edtTeacher.getText().toString());
			s.setStudentId(Integer.valueOf(edtStudentId.getText().toString()));
			s.setTimeIn(Integer.valueOf(edtTimeIn.getText().toString()));
			s.setTimeOut(edtTimeOut.getText().toString());
			s.setTestType(String.valueOf(spinnerTestType.getSelectedItem()));
			s.setTestSubject(String.valueOf(spinnerSubject.getSelectedItem()));
			s.setExtraNotes(edtExtraNotes.getText().toString());
			
			testingCenter.editStudent(s);
			
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			
			Toast.makeText(getApplicationContext(), "Changes Saved!", 
					Toast.LENGTH_LONG).show();
			
	//TODO: add spinners
		} //End If
	} // End Function
	
public void addStudent(){
	Student s = new Student();
	

	
	
	s.setCompleted(chkCompleted.isChecked());
	s.setFirstName(edtFirstName.getText().toString());
	s.setLastName(edtLastName.getText().toString());
	s.setTeacher(edtTeacher.getText().toString());
	s.setStudentId(Integer.valueOf(edtStudentId.getText().toString()));
	s.setTimeIn(Integer.valueOf(edtTimeIn.getText().toString()));
	s.setTimeOut(edtTimeOut.getText().toString());
	s.setTestType(String.valueOf(spinnerTestType.getSelectedItem()));
	s.setTestSubject(String.valueOf(spinnerSubject.getSelectedItem()));
	s.setExtraNotes(edtExtraNotes.getText().toString());
	
	Student newStudent = testingCenter.createStudent(s);
	
	
	txtTestId.setText(String.valueOf(newStudent.getTestId()));
	
	Intent intent = new Intent(this, MainActivity.class);
	startActivity(intent);
}

public void deleteStudent(){
	if (txtTestId.getText().toString().length() > 0){
		int testId = Integer.valueOf(txtTestId.getText().toString());
		Student s = testingCenter.getStudent(testId);
		testingCenter.deleteStudent(s);
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		
		Toast.makeText(getApplicationContext(), "Student Erased", 
		Toast.LENGTH_LONG).show();
		
	}
}
	
	
} // End Class Edit Student
