package com.example.rocio_wang.gourmetlists;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

/**
 * Created by Rocio_Wang on 2015/5/12.
 */
public class DBContentProvider extends ContentProvider {
    public static final String AUTHORITY ="com.example.rocio_wang.gourmetlists";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/RestaurantTable");
    DBHelper mDbHelper;
    SQLiteDatabase mDatabase;

    @Override
    public boolean onCreate() {
         mDbHelper = new DBHelper(getContext());
         //mDatabase = mDbHelper.getWritableDatabase();
        Log.d("LOG", "迴圈");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if(mDatabase == null) {
            mDatabase = mDbHelper.getWritableDatabase();
        }
        //SQLiteDatabase mDatabase = mDbHelper.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(DBQueryBuilder.RESTAURANT_TABLE);
        //builder.buildQuery(projection, selection, null, null, sortOrder, null);
        Cursor mCursor = builder.query(mDatabase,projection, selection, null, null, sortOrder, null);

       // mCursor.setNotificationUri(getContext().getContentResolver(), uri);
        Log.d("LOG傳值CP", mCursor.toString());
//        mDatabase.close();

        return mCursor;

    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        mDatabase = mDbHelper.getWritableDatabase();
//        DBQueryBuilder builder = new DBQueryBuilder();
        mDatabase.insert(DBQueryBuilder.RESTAURANT_TABLE,null, values);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
      //  mDb.delete(String.valueOf(uri), "title='" + mAddress + "'", null);
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
