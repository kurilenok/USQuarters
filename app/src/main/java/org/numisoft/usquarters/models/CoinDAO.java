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

    private final String SELECT = "SELECT name, fullname, description, year, imageId, proof, unc, fine, good, mintage ";

    public CoinDao(Context context) {
        this.context = context;
    }


    public List<Coin> getCoinsByTheme(Theme theme) {
        DBHelper dbHelper = new DBHelper(context, "coins", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT name, fullname, description, year, imageId, " +
                        "proof, unc, fine, good, mintage " +
                        "FROM catalog " +
                        "LEFT OUTER JOIN collection " +
                        "ON catalog.imageId = collection.coinId " +
                        "WHERE theme = ? ORDER BY imageId DESC", new String[]{theme.value});

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
            coin.setMintage(cursor.getString(cursor.getColumnIndex("mintage")));
            coins.add(coin);
        } while (cursor.moveToNext());

        cursor.close();
        db.close();
        dbHelper.close();

        return coins;
    }

    public List<Coin> getAllCoins(Theme theme) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT);
        query.append("FROM catalog ");
        query.append("LEFT OUTER JOIN collection ON catalog.imageId = collection.coinId ");
        query.append("WHERE theme = '");
        query.append(theme.value);
        query.append("' ORDER BY imageId DESC");

        return getSomeCoins(query.toString());
    }

    public List<Coin> getNeedCoins(Theme theme) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT);
        query.append("FROM catalog ");
        query.append("LEFT OUTER JOIN collection ON catalog.imageId = collection.coinId ");
        query.append("WHERE theme = '");
        query.append(theme.value);
        query.append("' AND (proof + unc + fine + good) = 0");
        query.append(" ORDER BY imageId DESC");

        return getSomeCoins(query.toString());
    }

    public List<Coin> getSwapCoins(Theme theme) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT);
        query.append("FROM catalog ");
        query.append("LEFT OUTER JOIN collection ON catalog.imageId = collection.coinId ");
        query.append("WHERE theme = '");
        query.append(theme.value);
        query.append("' AND (proof + unc + fine + good) > 1");
        query.append(" ORDER BY imageId DESC");

        return getSomeCoins(query.toString());
    }

    public List<Coin> getNotUncCoins(Theme theme) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT);
        query.append("FROM catalog ");
        query.append("LEFT OUTER JOIN collection ON catalog.imageId = collection.coinId ");
        query.append("WHERE theme = '");
        query.append(theme.value);
        query.append("' AND (fine + good) > 0");
        query.append(" AND (proof + unc) = 0");
        query.append(" ORDER BY imageId DESC");

        return getSomeCoins(query.toString());
    }

    public List<Coin> getSomeCoins(String query) {
        DBHelper dbHelper = new DBHelper(context, "coins", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{});

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
            coin.setMintage(cursor.getString(cursor.getColumnIndex("mintage")));
            coins.add(coin);
        } while (cursor.moveToNext());

        cursor.close();
        db.close();
        dbHelper.close();

        return coins;
    }

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
