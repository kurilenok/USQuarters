package org.numisoft.usquarters.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import org.numisoft.usquarters.utils.DBHelper;
import org.numisoft.usquarters.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kukolka on 14.08.16.
 */
public class CoinDao {

    Context context;

    public CoinDao(Context context) {
        this.context = context;
    }


    public List<Coin> getCoinsFromDB() {
        DBHelper dbHelper = new DBHelper(context, "coins", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM coins", new String[]{});

        List<Coin> coins = new ArrayList<>();

        if (!cursor.moveToFirst()) {
            return coins;
        }

        do {
            Coin coin = new Coin();

            coin.setName(cursor.getString(cursor.getColumnIndex("name")));
            coin.setYear(cursor.getString(cursor.getColumnIndex("year")));
            coin.setImageId(

                    context.getResources().getIdentifier(
                            cursor.getString(cursor.getColumnIndex("imageId")),
                            "drawable",
                            context.getPackageName()));
            coins.add(coin);

        } while (cursor.moveToNext());

        cursor.close();
        db.close();
        dbHelper.close();

        return coins;
    }

    public List<Coin> getCoinsByTheme(Theme theme) {
        DBHelper dbHelper = new DBHelper(context, "coins", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT name, year, imageId, unc FROM catalog " +
                        "LEFT OUTER JOIN collection ON catalog.imageId = collection.coinId " +
                        "WHERE theme = ? ", new String[]{theme.value});

        List<Coin> coins = new ArrayList<>();

        if (!cursor.moveToFirst()) {
            return coins;
        }

        do {
            Coin coin = new Coin();

            coin.setName(cursor.getString(cursor.getColumnIndex("name")));
            coin.setYear(cursor.getString(cursor.getColumnIndex("year")));
            coin.setUnc(cursor.getInt(cursor.getColumnIndex("unc")));

            coin.setImageId(context.getResources().getIdentifier(
                    cursor.getString(cursor.getColumnIndex("imageId")),
                    "drawable", context.getPackageName()));
            coins.add(coin);

        } while (cursor.moveToNext());

        cursor.close();
        db.close();
        dbHelper.close();

        return coins;
    }

    public Coin getCoinById(String imageId) {
        DBHelper dbHelper = new DBHelper(context, "coins", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM coins WHERE imageId = ?", new String[]{imageId});
        cursor.moveToFirst();

        Coin coin = new Coin();

        coin.setName(cursor.getString(cursor.getColumnIndex("name")));
        coin.setYear(cursor.getString(cursor.getColumnIndex("year")));

        cursor.close();
        db.close();
        dbHelper.close();

        return coin;
    }

    public void addUnc(Coin coin) {
        DBHelper dbHelper = new DBHelper(context, "coins", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        int newUnc = coin.getUnc() + 1;

        db.execSQL("UPDATE collection SET unc = " + newUnc + " WHERE coinId = 'park6'");

        Cursor cursor = db.rawQuery(
                "SELECT * FROM collection WHERE coinId = 'park6'", new String[]{});
        cursor.moveToFirst();

        Toast.makeText(context, Integer.toString(cursor.getInt(cursor.getColumnIndex("unc"))),
                Toast.LENGTH_SHORT).show();

        cursor.close();


        db.close();
        dbHelper.close();
    }

    public void deleteUnc(Coin coin) {
        DBHelper dbHelper = new DBHelper(context, "coins", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        int newUnc = coin.getUnc() - 1;

        db.execSQL("UPDATE collection SET unc = " + newUnc + " WHERE coinId = 'park6'");


        Cursor cursor = db.rawQuery(
                "SELECT * FROM collection WHERE coinId = 'park6'", new String[]{});
        cursor.moveToFirst();

        Toast.makeText(context, Integer.toString(cursor.getInt(cursor.getColumnIndex("unc"))),
                Toast.LENGTH_SHORT).show();

        cursor.close();
        db.close();
        dbHelper.close();
    }


}
