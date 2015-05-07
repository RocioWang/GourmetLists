package com.example.rocio_wang.gourmetlists;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by Rocio_Wang on 2015/5/7.
 */
public class DialogControl extends  AlertDialog.Builder{
    private static String DATABASE_TABLE = "titles";
    private SQLiteDatabase mDb;
    private DBHelper mDbHelper;

    DialogInterface.OnClickListener listener;

    public DialogControl(Context context, String add) {
        super(context);
        OptionDialog(context, add);
    }

    public void OptionDialog(final Context context, final String address){
        listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        break;
                    case 1:
                        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + address);
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                            context.startActivity(mapIntent);
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        mDbHelper = new DBHelper(context);
                        mDb = mDbHelper.getWritableDatabase();
                        mDb.delete(DATABASE_TABLE, "title='" + address + "'", null);
                        Toast.makeText(context, "刪除" + address, Toast.LENGTH_SHORT).show();
                        RunListView runList = new  RunListView();
                        runList.RunList(context);
                    break;
                }
            }
        };
    }
}