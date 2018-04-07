package ru.javawebinar.lunchpool.model;

import java.time.LocalDate;

public class Dish extends AbstractNamedDatedEntity {
    private final Integer priceInCents;

    private final Restaurant restaurant;

    public Dish(LocalDate creationDate, Integer priceInCents, Restaurant restaurant) {
        super(creationDate);
        this.priceInCents = priceInCents;
        this.restaurant = restaurant;
    }

    public Dish(Integer id, String name, LocalDate creationDate, Integer priceInCents, Restaurant restaurant) {
        super(id, name, creationDate);
        this.priceInCents = priceInCents;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "priceInCents=" + priceInCents +
                ", restaurant=" + restaurant +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
