package com.nimit.database.db.tables;
import android.content.ContentValues;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nimit.database.model.Todo;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.nimit.database.db.DbConsts.*;
/**
 * Created by Nimit Agg on 15-04-2017.
 */

public class TodoTable {
    public static final String TABLE_NAME = "todos";
    interface Columns{
        String ID = "id";
        String TASK = "task";
        String DONE = "done" ;
    }
    public static final String CMD_CREATE_TABLE =
            //"CREATE TABLE todo (id INTEGER PRIMARY KEY, task TEXT);";
            CREATE_TABLE_INE
            + TABLE_NAME
            + LBR
            + Columns.ID
            + TYPE_INT + TYPE_PK + TYPE_AI
            + COMMA
            + Columns.TASK
            + TYPE_TEXT
            + COMMA + Columns.DONE + TYPE_BOOL
            + RBR
            + SEMICOLON;


    public static void addTAsk(SQLiteDatabase db, Todo task){
        if(db.isReadOnly()) {
            Log.w(TAG, "addTAsk: Databse" );
            return ;
        }
        ContentValues cv = new ContentValues();
        cv.put(Columns.TASK, task.getTask());
        cv.put(Columns.DONE, task.isDone());
        db.insert(TABLE_NAME,
                null,
                cv);
    }
    public static ArrayList<Todo> fetchTodo(SQLiteDatabase db){
        Cursor c = db.query(
                TABLE_NAME,
                new String[]{Columns.ID,Columns.TASK, Columns.DONE},
                null,null,null,null,null
        );
        ArrayList<Todo> todolist = new ArrayList<>();
        if (c.moveToFirst()) {
            do{
                Todo tempTodo = new Todo(c.getInt(c.getColumnIndex(Columns.ID)),c.getString(c.getColumnIndex(Columns.TASK)),
                        c.getInt(c.getColumnIndex(Columns.DONE))==1);
                Log.w(TAG, "fetchTodo: "+  tempTodo.isDone());
                todolist.add(tempTodo);
            }while(c.moveToNext());
        }
        c.close();
        return todolist;
    }
    public static void setDone(SQLiteDatabase db, Todo updateTodo) {

        ContentValues cv = new ContentValues();
        cv.put(Columns.DONE,updateTodo.isDone());
        db.update(
                TABLE_NAME,
                cv,
                Columns.ID + "= ? ",
                new String[]{String.valueOf(updateTodo.getId())});
    }
}
