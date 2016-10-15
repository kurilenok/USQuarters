package org.numisoft.usquarters;

import android.content.Context;

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

    public List<Coin> getCoins() {
        List<Coin> coins = new ArrayList<>();
        String[] names = context.getResources().getStringArray(R.array.provinces);

        for (int i = 0; i <= 7; i++) {
            coins.add(new Coin(context.getResources().getIdentifier("us".concat(String.valueOf(i)),
                    "drawable", context.getPackageName()), names[i]));
        }
        return coins;
    }

    public List<Coin> getMoreCoins() {
        List<Coin> coins = new ArrayList<>();
        String[] names = context.getResources().getStringArray(R.array.parks);

        for (int i = 0; i <= 4; i++) {
            coins.add(new Coin(context.getResources().getIdentifier("us".concat(String.valueOf(i)),
                    "drawable", context.getPackageName()), names[i]));
        }
        return coins;
    }

}
