package com.example.rocio_wang.gourmetlists;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by Rocio_Wang on 2015/5/7.
 */
public class DialogControl extends  AlertDialog.Builder{
    public static final String AUTHORITY ="com.example.rocio_wang.gourmetlists";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/"+ "RESTAURANT_TABLE");

    DialogInterface.OnClickListener listener;
    DBContentProvider mContentProvider = new DBContentProvider();

    public DialogControl(Context context, String add) {
        super(context);
        OptionDialog(context, add);
    }



    public void OptionDialog(final Context context, final String address){
        listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Uri uri = Uri.parse("tel:123456089");
                        Intent phoneCall = new Intent(Intent.ACTION_CALL, uri);
                        context.startActivity(phoneCall);
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
                        mContentProvider.delete(CONTENT_URI, "title='" + address + "'", null);;
                        Toast.makeText(context, "刪除" + address, Toast.LENGTH_SHORT).show();
                    break;

                }
            }
        };
    }
}