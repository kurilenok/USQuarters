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

    private Context context;

    private final String SELECT = "SELECT name, fullname, description, year, imageId, " +
            "catalog.coinId, unc, aunc, fine, good, mintage, mark ";

    public CoinDao(Context context) {
        this.context = context;
    }

//    public List<Coin> getAllCoins(Theme theme) {
//        StringBuilder query = new StringBuilder();
//        query.append(SELECT);
//        query.append("FROM catalog ");
//        query.append("LEFT OUTER JOIN collection ON catalog.coinId = collection.coinId ");
//        query.append("WHERE theme = '");
//        query.append(theme.value);
//        query.append("' ORDER BY imageId DESC");
//
//        return getSomeCoins(query.toString());
//    }

    public List<Coin> getAllCoins(Theme theme1, Theme theme2) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT);
        query.append("FROM catalog ");
        query.append("LEFT OUTER JOIN collection ON catalog.coinId = collection.coinId ");
        query.append("WHERE (theme = '");
        query.append(theme1.value);
        query.append("' OR theme ='");
        query.append(theme2.value);
        query.append("') ORDER BY catalog.coinId DESC");

        return getSomeCoins(query.toString());
    }

//    public List<Coin> getNeedCoins(Theme theme) {
//        StringBuilder query = new StringBuilder();
//        query.append(SELECT);
//        query.append("FROM catalog ");
//        query.append("LEFT OUTER JOIN collection ON catalog.coinId = collection.coinId ");
//        query.append("WHERE theme = '");
//        query.append(theme.value);
//        query.append("' AND (unc + aunc + fine + good) = 0");
//        query.append(" ORDER BY imageId DESC");
//
//        return getSomeCoins(query.toString());
//    }

    public List<Coin> getNeedCoins(Theme theme1, Theme theme2) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT);
        query.append("FROM catalog ");
        query.append("LEFT OUTER JOIN collection ON catalog.coinId = collection.coinId ");
        query.append("WHERE (theme = '");
        query.append(theme1.value);
        query.append("' OR theme ='");
        query.append(theme2.value);
        query.append("') AND (unc + aunc + fine + good) = 0");
        query.append(" ORDER BY catalog.coinId DESC");

        return getSomeCoins(query.toString());
    }

//    public List<Coin> getSwapCoins(Theme theme) {
//        StringBuilder query = new StringBuilder();
//        query.append(SELECT);
//        query.append("FROM catalog ");
//        query.append("LEFT OUTER JOIN collection ON catalog.coinId = collection.coinId ");
//        query.append("WHERE theme = '");
//        query.append(theme.value);
//        query.append("' AND (unc + aunc + fine + good) > 1");
//        query.append(" ORDER BY imageId DESC");
//
//        return getSomeCoins(query.toString());
//    }

    public List<Coin> getSwapCoins(Theme theme1, Theme theme2) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT);
        query.append("FROM catalog ");
        query.append("LEFT OUTER JOIN collection ON catalog.coinId = collection.coinId ");
        query.append("WHERE (theme = '");
        query.append(theme1.value);
        query.append("' OR theme ='");
        query.append(theme2.value);
        query.append("') AND (unc + aunc + fine + good) > 1");
        query.append(" ORDER BY catalog.coinId DESC");

        return getSomeCoins(query.toString());
    }

//    public List<Coin> getNotUncCoins(Theme theme) {
//        StringBuilder query = new StringBuilder();
//        query.append(SELECT);
//        query.append("FROM catalog ");
//        query.append("LEFT OUTER JOIN collection ON catalog.coinId = collection.coinId ");
//        query.append("WHERE theme = '");
//        query.append(theme.value);
//        query.append("' AND (aunc + fine + good) > 0");
//        query.append(" AND unc = 0");
//        query.append(" ORDER BY imageId DESC");
//
//        return getSomeCoins(query.toString());
//    }

    public List<Coin> getNotUncCoins(Theme theme1, Theme theme2) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT);
        query.append("FROM catalog ");
        query.append("LEFT OUTER JOIN collection ON catalog.coinId = collection.coinId ");
        query.append("WHERE (theme = '");
        query.append(theme1.value);
        query.append("' OR theme ='");
        query.append(theme2.value);
        query.append("') AND (aunc + fine + good) > 0");
        query.append(" AND unc = 0");
        query.append(" ORDER BY catalog.coinId DESC");

        return getSomeCoins(query.toString());
    }

    public List<Coin> getSomeCoins(String query) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{});

        List<Coin> coins = new ArrayList<>();
        if (!cursor.moveToFirst()) return coins;

        do {
            Coin coin = new Coin();
            coin.setName(cursor.getString(cursor.getColumnIndex("name")));
            coin.setFullname(cursor.getString(cursor.getColumnIndex("fullname")));
            coin.setYear(cursor.getInt(cursor.getColumnIndex("year")));
            coin.setUnc(cursor.getInt(cursor.getColumnIndex("unc")));
            coin.setAUnc(cursor.getInt(cursor.getColumnIndex("aunc")));
            coin.setFine(cursor.getInt(cursor.getColumnIndex("fine")));
            coin.setGood(cursor.getInt(cursor.getColumnIndex("good")));
            coin.setImageId(cursor.getString(cursor.getColumnIndex("imageId")));
            coin.setCoinId(cursor.getString(cursor.getColumnIndex("coinId")));
            coin.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            coin.setMintage(cursor.getString(cursor.getColumnIndex("mintage")));
            coin.setMark(cursor.getString(cursor.getColumnIndex("mark")));
            coins.add(coin);
        } while (cursor.moveToNext());

        cursor.close();
        db.close();
        dbHelper.close();

        return coins;
    }

    public void updateCoin(Coin coin) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.execSQL(
                "UPDATE collection SET unc = ?, aunc = ?, fine = ?, good = ? WHERE coinId = ?",
                new Object[]{coin.getUnc(), coin.getAUnc(), coin.getFine(), coin.getGood(),
                        coin.getCoinId()});
        db.close();
        dbHelper.close();
    }

}
