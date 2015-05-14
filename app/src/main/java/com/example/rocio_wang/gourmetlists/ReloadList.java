package com.example.rocio_wang.gourmetlists;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Rocio_Wang on 2015/5/7.
 */
public class ReloadList {
    public static final String AUTHORITY ="com.example.rocio_wang.gourmetlists";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/"+ "RestaurantTable");
    private static final String[] COLUMNAME  = new String[]{"_id", "title", "address"};
    private AlertDialog mDialog;
    private String mMapAddress;
    private ArrayList<String> mStringList =  new ArrayList<>();
    private final String mDialogTitle = "選擇項目";
    private final String mDialogChooseCall = "Call";
    private final String mDialogChooseMap = "Google Maps";
    private final String mDialogChooseDone = "Got it!";
    private final String mDialogChooseDel = "Delete";



   //將Adpter傳入Listview
    public void RunList(final Context context){

        ContentResolver resolver = context.getContentResolver();
        Cursor mCursor = resolver.query(CONTENT_URI, COLUMNAME , null, null, null);

        Log.d("LOG傳值RUN", mCursor.toString());
        mCursor.moveToFirst();
        Log.d("LOG傳值FIRST", mCursor.toString());
        for (int i = 0; i < mCursor.getCount(); i++){
            String str = mCursor.getString(1);
            mStringList.add(str);
            Log.d("LOG", "迴圈");
            mCursor.moveToNext();
        }

        mCursor.close();


        ArrayAdapter<String> mListAdapter = new ArrayAdapter<>(context, R.layout.defaultlist, mStringList);
        MainActivity.mMainListView.setAdapter(mListAdapter);

        MainActivity.mMainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mMapAddress = adapterView.getItemAtPosition(i).toString();
                DialogControl dialogControl = new  DialogControl(context, mMapAddress);
                String[] options = {mDialogChooseCall, mDialogChooseMap, mDialogChooseDone, mDialogChooseDel};
                dialogControl.setTitle(mDialogTitle);
                dialogControl.setItems(options, dialogControl.listener);
                dialogControl.setNegativeButton("取消", null);
                mDialog = dialogControl.create();
                mDialog.show();
            }
        });
    }

}
