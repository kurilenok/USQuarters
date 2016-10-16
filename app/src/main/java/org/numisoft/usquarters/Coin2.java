package org.numisoft.usquarters;

/**
 * Created by kukolka on 10/16/2016.
 */

public class Coin2 {

    String name;
    String year;
    String imageString;

    public Coin2() {
    }

    public Coin2(String name, String year, String imageString) {
        this.name = name;
        this.year = year;
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

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }
}
