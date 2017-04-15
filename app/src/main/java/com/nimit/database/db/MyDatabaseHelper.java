package com.nimit.database.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nimit.database.db.tables.TodoTable;

/**
 * Created by Nimit Agg on 15-04-2017.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "todo.db";
    public static final int DB_VER = 1;
    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TodoTable.CMD_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
