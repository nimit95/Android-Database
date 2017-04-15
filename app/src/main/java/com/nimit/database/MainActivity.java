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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText newTodo;
    private ListView lvTodo;
    ArrayList<String> todo = new ArrayList<>();
    private Button addTodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newTodo = (EditText) findViewById(R.id.task);
        lvTodo = (ListView) findViewById(R.id.lvTodo);
        addTodo = (Button) findViewById(R.id.lvAddButton);

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        todo = TodoTable.fetchTodo(db);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                            this,
                                            android.R.layout.simple_list_item_1,
                                            android.R.id.text1,
                                            todo) ;
        lvTodo.setAdapter(adapter);
        addTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todo.add(newTodo.getText().toString());
                adapter.notifyDataSetChanged();
                TodoTable.addTAsk(db,newTodo.getText().toString() );
            }
        });
    }
}
