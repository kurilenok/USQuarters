package org.numisoft.usquarters;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by kukolka on 10/16/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    Context context;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS coins");
        db.execSQL("CREATE TABLE coins (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, year TEXT, imageId TEXT)");

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Coin>>(){}.getType();
        List<Coin> coins = gson.fromJson(JSONConverter.convert(context, "test.json"), listType);

        for (Coin c : coins) {
            ContentValues cv = new ContentValues();
            cv.put("name", c.getName());
            cv.put("year", c.getYear());
            cv.put("imageId", c.getImageString());
            db.insert("coins", null, cv);
        }

//        db.execSQL("INSERT INTO coins (name, year, imageId) VALUES ('Hot Springs', '2010', 'us0')");
//        db.execSQL("INSERT INTO coins (name, year, imageId) VALUES ('Gettysburg', '2011', 'us1')");
//        db.execSQL("INSERT INTO coins (name, year, imageId) VALUES ('Glacier', '2011', 'us2')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
