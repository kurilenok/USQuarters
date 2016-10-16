package org.numisoft.usquarters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kukolka on 10/16/2016.
 */

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE coins (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, year TEXT, imageId TEXT)");
        db.execSQL("INSERT INTO coins (name, year, imageId) VALUES ('Hot Springs', '2010', 'us0')");
        db.execSQL("INSERT INTO coins (name, year, imageId) VALUES ('Gettysburg', '2011', 'us1')");
        db.execSQL("INSERT INTO coins (name, year, imageId) VALUES ('Glacier', '2011', 'us2')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
