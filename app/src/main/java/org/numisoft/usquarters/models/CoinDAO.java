package org.numisoft.usquarters.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.numisoft.usquarters.utils.DBHelper;

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


    public List<Coin> getCoinsByTheme(Theme theme) {
        DBHelper dbHelper = new DBHelper(context, "coins", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT name, fullname, description, year, imageId, proof, unc, fine, good " +
                        "FROM catalog " +
                        "LEFT OUTER JOIN collection " +
                        "ON catalog.imageId = collection.coinId " +
                        "WHERE theme = ?", new String[]{theme.value});

        List<Coin> coins = new ArrayList<>();
        if (!cursor.moveToFirst()) return coins;

        do {
            Coin coin = new Coin();
            coin.setName(cursor.getString(cursor.getColumnIndex("name")));
            coin.setFullname(cursor.getString(cursor.getColumnIndex("fullname")));
            coin.setYear(cursor.getString(cursor.getColumnIndex("year")));
            coin.setProof(cursor.getInt(cursor.getColumnIndex("proof")));
            coin.setUnc(cursor.getInt(cursor.getColumnIndex("unc")));
            coin.setFine(cursor.getInt(cursor.getColumnIndex("fine")));
            coin.setGood(cursor.getInt(cursor.getColumnIndex("good")));
            coin.setImageId(cursor.getString(cursor.getColumnIndex("imageId")));
            coin.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            coins.add(coin);
        } while (cursor.moveToNext());

        cursor.close();
        db.close();
        dbHelper.close();

        return coins;
    }

//    public Coin getCoinById(String imageId) {
//        DBHelper dbHelper = new DBHelper(context, "coins", null, 1);
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery(
//                "SELECT * FROM coins WHERE imageId = ?", new String[]{imageId});
//        cursor.moveToFirst();
//
//        Coin coin = new Coin();
//
//        coin.setName(cursor.getString(cursor.getColumnIndex("name")));
//        coin.setYear(cursor.getString(cursor.getColumnIndex("year")));
//
//        cursor.close();
//        db.close();
//        dbHelper.close();
//
//        return coin;
//    }

    public void updateCoin(Coin coin) {
        DBHelper dbHelper = new DBHelper(context, "coins", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.execSQL(
                "UPDATE collection SET proof = ?, unc = ?, fine = ?, good = ? WHERE coinId = ?",
                new Object[]{coin.getProof(), coin.getUnc(), coin.getFine(), coin.getGood(),
                        coin.getImageId()});
        db.close();
        dbHelper.close();
    }

}
