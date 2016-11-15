package org.numisoft.usquarters.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.numisoft.usquarters.R;
import org.numisoft.usquarters.models.Coin;

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
        db.execSQL("DROP TABLE IF EXISTS catalog");
        db.execSQL("CREATE TABLE catalog (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, fullname TEXT, mintage TEXT, " +
                "year TEXT, imageId TEXT, mark TEXT, " +
                "theme TEXT, description TEXT)");

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Coin>>(){}.getType();
        List<Coin> coins = gson.fromJson(
                JSONConverter.convert(context, context.getResources().getString(R.string.json)), listType);

        for (Coin c : coins) {
            ContentValues cv = new ContentValues();
            cv.put("name", c.getName());
            cv.put("fullname", c.getFullname());
            cv.put("description", c.getDescription());
            cv.put("mintage", c.getMintage());
            cv.put("mark", c.getMark());
            cv.put("year", c.getYear());
            cv.put("imageId", c.getImageId());
            cv.put("theme", c.getTheme());
            db.insert("catalog", null, cv);
        }

        db.execSQL("DROP TABLE IF EXISTS collection");
        db.execSQL("CREATE TABLE collection (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "coinId TEXT, unc INTEGER, aunc INTEGER, fine INTEGER, good INTEGER)");

        for (Coin c : coins) {
            ContentValues cv2 = new ContentValues();
            cv2.put("coinId", c.getImageId());
            cv2.put("unc", 0);
            cv2.put("aunc", 0);
            cv2.put("fine", 0);
            cv2.put("good", 0);
            db.insert("collection", null, cv2);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
