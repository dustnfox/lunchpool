package com.dustnfox.lunchpool.to;

import com.dustnfox.lunchpool.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Option {
    private final int id;
    private final String name;
    private final String address;
    private final String desctiption;
    List<MenuEntryDTO> menuEntries = new ArrayList<>();

    public Option(Restaurant restaurant) {
        id = restaurant.getId();
        name = restaurant.getName();
        address = restaurant.getAddress();
        desctiption = restaurant.getDescription();
    }

    public void setMenuEntries(MenuEntryDTO menuEntry) {
        menuEntries.add(menuEntry);
    }
}
