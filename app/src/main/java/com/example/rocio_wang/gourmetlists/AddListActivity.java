package com.example.rocio_wang.gourmetlists;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


public class AddListActivity extends Activity {
    public static final String AUTHORITY ="com.example.rocio_wang.gourmetlists";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/"+ "RestaurantTable");
    private static final String TABLE_NAME = "RestaurantTable";
    private EditText mTitleText, mAddressText, mPhoneText;
    public String mTitle;
    public String mAddress;

    DBContentProvider mContentProvider = new DBContentProvider();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        mTitleText = (EditText)findViewById(R.id.editTextTitle);
        mAddressText = (EditText)findViewById(R.id.editTextAdd);
        mPhoneText = (EditText)findViewById(R.id.editTextPhone);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_Done:
                ContentValues cv = new ContentValues();
                cv.put("title", mTitleText.getText().toString());
                cv.put("address", mAddressText.getText().toString());
                cv.put("phoneNumber", mPhoneText.getText().toString());
                ContentResolver resolver = getContentResolver();

                resolver.insert(CONTENT_URI, cv);
                Log.d("LOG傳值CV1", cv.toString());
                Toast.makeText(this, "新增" + mTitleText.getText().toString(), Toast.LENGTH_SHORT).show();
                finish();
                break;
        }


        //return true;
        return super.onOptionsItemSelected(item);

    }


}
