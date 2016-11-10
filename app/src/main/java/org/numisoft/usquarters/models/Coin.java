package org.numisoft.usquarters.models;

import java.io.Serializable;

/**
 * Created by kukolka on 10/15/2016.
 */

public class Coin implements Serializable {

    private String name;
    private String fullname;
    private String year;
    private String theme;
    private String imageId;
    private int proof;
    private int unc;
    private int fine;
    private int good;
    private String description;
    private String mintage;

    public Coin() {}

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getUnc() {
        return unc;
    }

    public void setUnc(int unc) {
        this.unc = unc;
    }

    public int getProof() {
        return proof;
    }

    public void setProof(int proof) {
        this.proof = proof;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMintage() {
        return mintage;
    }

    public void setMintage(String mintage) {
        this.mintage = mintage;
    }
}