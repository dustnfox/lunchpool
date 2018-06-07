package com.dustnfox.lunchpool.to;

import com.dustnfox.lunchpool.model.Restaurant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Option {
    private final int id;
    private final String name;
    private final String address;
    private final String description;
    List<MenuEntryDTO> menuEntries = new ArrayList<>();

    public Option(Restaurant restaurant) {
        id = restaurant.getId();
        name = restaurant.getName();
        address = restaurant.getAddress();
        description = restaurant.getDescription();
    }

    public void setMenuEntries(MenuEntryDTO... entries) {
        menuEntries.addAll(Arrays.asList(entries));
    }
}
