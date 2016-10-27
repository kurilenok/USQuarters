package org.numisoft.usquarters.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.numisoft.usquarters.utils.DBHelper;
import org.numisoft.usquarters.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kukolka on 14.08.16.
 */
public class CoinDAO {

    Context context;

    public CoinDAO(Context context) {
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

    public List<Coin> getCoins() {
        List<Coin> coins = new ArrayList<>();
        String[] names = context.getResources().getStringArray(R.array.provinces);

        for (int i = 0; i <= 6; i++) {
            coins.add(new Coin(
                    names[i],
                    "2000",
                    context.getResources().getIdentifier("park".concat(String.valueOf(i+1)),
                            "drawable", context.getPackageName())));
        }
        return coins;
    }

    public List<Coin> getMoreCoins() {
        List<Coin> coins = new ArrayList<>();
        String[] names = context.getResources().getStringArray(R.array.parks);

        for (int i = 0; i <= 4; i++) {
            coins.add(new Coin(names[i], "D\n2000",
                    context.getResources().getIdentifier("us".concat(String.valueOf(i)),
                            "drawable", context.getPackageName())));
        }
        return coins;
    }


    public List<Coin> getCoinsByMint(Mint mint) {
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
}
