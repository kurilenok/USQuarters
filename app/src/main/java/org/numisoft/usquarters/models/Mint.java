package org.numisoft.usquarters.models;

/**
 * Created by kukolka on 10/18/2016.
 */

public enum Mint {

    P ("P"),
    D ("D"),
    S ("S");

    final String value;

    Mint(String value) {
        this.value = value;
    }

}
