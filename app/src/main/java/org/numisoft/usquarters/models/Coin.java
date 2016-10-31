package org.numisoft.usquarters.models;

/**
 * Created by kukolka on 10/15/2016.
 */

public class Coin {

    String name;
    String year;
    String imageString;
    String mint;
    String category;
    int imageId;

    public Coin() {}

    public Coin(String name, String year, String imageString) {
        this.name = name;
        this.year = year;
        this.imageString = imageString;
    }

    public Coin(String name, String year, int imageId) {
        this.name = name;
        this.year = year;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

}