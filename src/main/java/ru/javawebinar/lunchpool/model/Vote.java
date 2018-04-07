package ru.javawebinar.lunchpool.model;

import java.time.LocalDate;

public class Vote extends AbstractNamedDatedEntity {

    private final User user;

    private final Restaurant restaurant;

    public Vote(User user, Restaurant restaurant) {
        super("");
        this.user = user;
        this.restaurant = restaurant;
    }

    public Vote(LocalDate creationDate, User user, Restaurant restaurant) {
        super(creationDate);
        this.user = user;
        this.restaurant = restaurant;
    }

    public Vote(Integer id, String name, LocalDate creationDate, User user, Restaurant restaurant) {
        super(id, name, creationDate);
        this.user = user;
        this.restaurant = restaurant;
    }
}
