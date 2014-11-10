package com.example.monarchiktodolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class EditTaskActivity extends Activity {
	private ToDoList toDoList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_task);
		
		toDoList = new ToDoList(this);
		
		Intent intent = getIntent();
		int taskId = intent.getIntExtra("com.example.lastnametodolist.taskId", 0);
		if (taskId != 0){
			populateTaskData(taskId);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_task, menu);
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
	public void handleClick(View v){
		switch (v.getId()){
		case R.id.btnAddTask:
			addToDo();
			break;
		case R.id.btnEditTask:
			editTaskData();
			break;
		case R.id.btnDeleteTask:
			deleteTask();
			break;
		case R.id.btnMainMenu:
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
	}
	

	
	private void populateTaskData(int taskId){
		TextView txtId = (TextView)findViewById(R.id.txtID);
		CheckBox chkPriority = (CheckBox)findViewById(R.id.chkPriority);
		EditText edtDate = (EditText)findViewById(R.id.edtDueDate);
		EditText edtTask = (EditText)findViewById(R.id.edtTask);
		CheckBox chkBoxDone = (CheckBox)findViewById(R.id.chkDone);
		
		Task t = toDoList.getTask(taskId);
		
		txtId.setText(String.valueOf(t.getId()));
		chkPriority.setChecked(t.getPriority());
		edtDate.setText(t.getDate());
		edtTask.setText(t.getTaskDetails());
		chkBoxDone.setChecked(t.getCompleted());
	}
	
	private void editTaskData(){
		TextView txtId = (TextView)findViewById(R.id.txtID);
		CheckBox chkPriority = (CheckBox)findViewById(R.id.chkPriority);
		EditText edtDate = (EditText)findViewById(R.id.edtDueDate);
		EditText edtTask = (EditText)findViewById(R.id.edtTask);
		CheckBox chkBoxDone = (CheckBox)findViewById(R.id.chkDone);
		
		
		if (txtId.getText().toString().length() > 0){
			int id = Integer.valueOf(txtId.getText().toString());
			Task t = toDoList.getTask(id);
			
			t.setPriority(chkPriority.isChecked());
			t.setDate(edtDate.getText().toString());
			t.setTask(edtTask.getText().toString());
			t.setCompleted(chkBoxDone.isChecked());
			
			toDoList.editTask(t);
			
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			
			
		}
	}
	
private void addToDo(){
	TextView txtId = (TextView)findViewById(R.id.txtID);
	CheckBox chkPriority = (CheckBox)findViewById(R.id.chkPriority);
	EditText edtDate = (EditText)findViewById(R.id.edtDueDate);
	EditText edtTask = (EditText)findViewById(R.id.edtTask);
	CheckBox chkBoxDone = (CheckBox)findViewById(R.id.chkDone);
	
	Task t = new Task();
	
	t.setPriority(chkPriority.isChecked());
	t.setDate(edtDate.getText().toString());
	t.setTask(edtTask.getText().toString());
	t.setCompleted(chkBoxDone.isChecked());
	
	Task newTask = toDoList.createTask(t);
	
	txtId.setText(String.valueOf(newTask.getId()));
	
	
}


private void deleteTask(){
	TextView textID = (TextView)findViewById(R.id.txtID);
	
	if (textID.getText().toString().length() > 0) {
		int id = Integer.valueOf(textID.getText().toString());
		Task t = toDoList.getTask(id);
		toDoList.deleteTask(t);
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
	
	
}

