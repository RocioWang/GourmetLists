package com.example.rocio_wang.gourmetlists;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


public class AddListActivity extends Activity {
    private static String DATABASE_TABLE = "titles";
    private SQLiteDatabase mDb;
    private DBHelper mDbHelper;
    private EditText mTitleText, mAddressText, mPhoneText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        mDbHelper = new DBHelper(this);
        mDb = mDbHelper.getWritableDatabase();
        mTitleText = (EditText)findViewById(R.id.editTextTitle);
        mAddressText = (EditText)findViewById(R.id.editTextAdd);
        mPhoneText = (EditText)findViewById(R.id.editTextPhone);
    }

    @Override
    protected void onStop(){
        super.onStop();
        mDb.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_Done) {
            ContentValues cv = new ContentValues();
            cv.put("title", mTitleText.getText().toString());
            cv.put("address", mAddressText.getText().toString());
            mDb.insert(DATABASE_TABLE, null, cv);
            Toast.makeText(this, "新增"+mTitleText.getText().toString(), Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
