package org.numisoft.usquarters.models;

/**
 * Created by kukolka on 10/18/2016.
 */

public enum Theme {

    STATES ("Statehood Quarters"),
    STATES_P ("Statehood Quarters (P)"),
    STATES_D ("Statehood Quarters (D)"),
    PARKS ("America the Beautiful"),
    PARKS_P ("America the Beautiful (P)"),
    PARKS_D ("America the Beautiful (D)"),
    PRESIDENTS ("Presidential Dollars"),
    PRESIDENTS_P ("Presidential Dollars (P)"),
    PRESIDENTS_D ("Presidential Dollars (D)");

    final String value;

    Theme(String value) {
        this.value = value;
    }

}
