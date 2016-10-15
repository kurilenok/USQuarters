package org.numisoft.usquarters;

/**
 * Created by kukolka on 10/15/2016.
 */

public class Coin {

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