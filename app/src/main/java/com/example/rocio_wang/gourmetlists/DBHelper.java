package com.example.rocio_wang.gourmetlists;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rocio_Wang on 2015/5/6.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final  String DATABASE_NAME="Restaurant";
    private static final  int DATABASE_VERSION=1;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE titles (_id integer" + " primary key autoincrement," + "title text no null, address real no null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS titles");
        onCreate(db);
    }
}
