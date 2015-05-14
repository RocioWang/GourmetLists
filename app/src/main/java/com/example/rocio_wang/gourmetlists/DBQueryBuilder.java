package com.example.rocio_wang.gourmetlists;

import android.database.sqlite.SQLiteQueryBuilder;

/**
 * Created by Rocio_Wang on 2015/5/14.
 */
public class DBQueryBuilder  extends SQLiteQueryBuilder {

    public static final String RESTAURANT_TABLE = "RestaurantTable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONENUMBER = "phoneNumber";
    public static final String DATABASE_QUERYALL = "SELECT * FROM " + RESTAURANT_TABLE;
    public static final String DATABASE_INSERT = "INSERT INTO " + RESTAURANT_TABLE + " (title) VALUES ('TEST') ";

}
