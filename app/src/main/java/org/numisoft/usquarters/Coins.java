package org.numisoft.usquarters;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kukolka on 14.08.16.
 */
public class Coins {

    Context context;

    public Coins(Context context) {
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

    class Coin {

        int imageId;
        String name;

        public Coin(int imageId, String name) {
            this.imageId = imageId;
            this.name = name;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
