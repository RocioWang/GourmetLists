package com.example.rocio_wang.gourmetlists;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Rocio_Wang on 2015/5/7.
 */
public class RunListView {
    private static String DATABASE_TABLE = "titles";
    private SQLiteDatabase mDb;
    private DBHelper mDbHelper;
    private AlertDialog mDialog;
    private String mMapAddress;
    private final String mDialogTitle = "選擇項目";
    private final String mDialogChoose0 = "Call";
    private final String mDialogChoose1 = "Google Maps";
    private final String mDialogChoose2 = "Got it!";
    private final String mDialogChoose3 = "Delete";
    ArrayAdapter<String> mAdapter = MainActivity.mListAdapter;
    ListView mListView =  MainActivity.mMainListView;




    public void RunList(final Context context){
        mDbHelper = new DBHelper(context);
        mDb = mDbHelper.getWritableDatabase();
        String[] colName = new String[]{"_id", "title", "address"};
        Cursor c = mDb.query(DATABASE_TABLE, colName, null, null, null, null, null);
        ArrayList<String> mStringList = new ArrayList<>();
        for (int i = 0; i < colName.length; i++)
            c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            String str = c.getString(1);
            mStringList.add(str.toString());
            c.moveToNext();
        }
        //ListView
        mAdapter = new ArrayAdapter<>(context, R.layout.defaultlist, mStringList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mMapAddress = adapterView.getItemAtPosition(i).toString();
                DialogControl dialogControl = new  DialogControl(context, mMapAddress);
                String[] options = {mDialogChoose0, mDialogChoose1, mDialogChoose2, mDialogChoose3};
                dialogControl.setTitle(mDialogTitle);
                dialogControl.setItems(options, dialogControl.listener);
                dialogControl.setNegativeButton("取消", null);
                mDialog = dialogControl.create();
                mDialog.show();
            }
        });

    }



}
