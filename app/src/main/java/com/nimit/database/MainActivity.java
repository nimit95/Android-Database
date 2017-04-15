package com.nimit.database;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.nimit.database.db.MyDatabaseHelper;
import com.nimit.database.db.tables.TodoTable;
import com.nimit.database.model.Todo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText newTodo;
    private ListView lvTodo;
    ArrayList<Todo> todo = new ArrayList<>();
    private Button addTodo;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newTodo = (EditText) findViewById(R.id.task);
        lvTodo = (ListView) findViewById(R.id.lvTodo);
        addTodo = (Button) findViewById(R.id.lvAddButton);

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        todo = refreashList(db);
        /*final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                            this,
                                            android.R.layout.simple_list_item_1,
                                            android.R.id.text1,
                                            todo) ;*/
        final TodoListAdapter adapter = new TodoListAdapter(this, todo);
        lvTodo.setAdapter(adapter);
        addTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Todo temp = new Todo(newTodo.getText().toString(), false);
                //todo.add(temp);
                //adapter.notifyDataSetChanged();
                //TodoTable.addTAsk(db, temp );
                TodoTable.addTAsk(db, temp);
                todo = refreashList(db);
                adapter.update(todo);
            }
        });
    }
    ArrayList<Todo> refreashList(SQLiteDatabase db){
        return TodoTable.fetchTodo(db);
    }
    void setTodoDone(Todo todoDone) {
        TodoTable.setDone(db, todoDone);
        refreashList(db);
    }
}
