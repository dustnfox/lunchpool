package com.dustnfox.lunchpool.model;

import java.time.LocalDate;

public class MenuEntry extends AbstractDatedEntity {
    private final Integer priceInCents;
    private final Restaurant restaurant;
    private final String Description;

    public MenuEntry(LocalDate date, Integer priceInCents, Restaurant restaurant, String description) {
        super(date);
        this.priceInCents = priceInCents;
        this.restaurant = restaurant;
        Description = description;
    }

    public MenuEntry(Integer id, LocalDate date, Integer priceInCents, Restaurant restaurant, String description) {
        super(id, date);
        this.priceInCents = priceInCents;
        this.restaurant = restaurant;
        Description = description;
    }

    @Override
    public String toString() {
        return "MenuEntry{" +
                "priceInCents=" + priceInCents +
                ", restaurant=" + restaurant +
                ", id=" + id +
                '}';
    }
}
