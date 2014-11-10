package com.example.monarchiktodolist;

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


public class MainActivity extends Activity {
	private ListView lstTasks;
	private List <Task> tasks;
	private ToDoList toDoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       setContentView(R.layout.activity_main);
       tasks = new ArrayList<Task>(0);
       toDoList = new ToDoList(this);
       
       lstTasks = (ListView)findViewById(R.id.lstTasks);
       
       populateListViewTasks();
       
       lstTasks.setOnItemClickListener(listViewListener);
       
        
        
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
    //Handle Click
    public void handleClick(View v){
    	//Id gets passed into function from UI)
    	switch(v.getId()){
    	//If this is clicked
    	case R.id.btnAddToDo:
    		//We are going from this view to the edit task activity class
    		Intent addTask = new Intent(this, EditTaskActivity.class);
    		startActivity(addTask);
    		break;
    	} //End Case
    }//End Handle Click
    
    private void populateListViewTasks(){
    	//List of Strings
    	List <String> taskStrings = new ArrayList<String>(0);
    	
    	tasks = toDoList.getAllTasks();
    	
    	for (int i = 0; i < tasks.size(); i++){
    		taskStrings.add(tasks.get(i).toString());
    	}
    
    		ArrayAdapter<String> arrayAdapter = 
    				new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,taskStrings);
    		
    		lstTasks.setAdapter(arrayAdapter);
    }
    
    private OnItemClickListener listViewListener = new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			int taskId = tasks.get(position).getId();
			
			goToEditTask(taskId);
			
		}

    };
    
   protected void goToEditTask(int id){
	   Intent editTask = new Intent(this, EditTaskActivity.class);
	   editTask.putExtra("com.example.monarchiktodolist.taskId", id);
	   startActivity(editTask);
   }
} //End Class
