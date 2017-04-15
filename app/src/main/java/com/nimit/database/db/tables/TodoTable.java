package com.nimit.database.db.tables;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
    }
    public static final String CMD_CREATE_TABLE =
            //"CREATE TABLE todo (id INTEGER PRIMARY KEY, task TEXT);";
            CREATE_TABLE_INE
            + TABLE_NAME
            + LBR
            + Columns.ID
            + TYPE_INT
            + TYPE_PK
            + COMMA
            + Columns.TASK
            + TYPE_TEXT
            + RBR
            + SEMICOLON;
    public static void addTAsk(SQLiteDatabase db, String task){
        if(db.isReadOnly()) {
            Log.w(TAG, "addTAsk: Databse" );
            return ;
        }
        ContentValues cv = new ContentValues();
        cv.put(Columns.TASK, task);
        db.insert(TABLE_NAME,
                null,
                cv);
    }
    public static ArrayList<String> fetchTodo(SQLiteDatabase db){
        Cursor c = db.query(
                TABLE_NAME,
                new String[]{Columns.TASK},
                null,null,null,null,null
        );
        ArrayList<String> todolist = new ArrayList<>();
        if (c.moveToFirst()) {
            do{
                todolist.add(c.getString(c.getColumnIndex(Columns.TASK)));
            }while(c.moveToNext());
        }
        c.close();
        return todolist;
    }
}
