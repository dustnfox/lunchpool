package ru.javawebinar.lunchpool.model;

import java.time.LocalDate;

public class Vote extends AbstractDatedEntity {

    private final User user;

    private final Restaurant restaurant;

    public Vote(User user, Restaurant restaurant) {
        super(null, LocalDate.now());
        this.user = user;
        this.restaurant = restaurant;
    }

    public Vote(LocalDate date, User user, Restaurant restaurant) {
        super(date);
        this.user = user;
        this.restaurant = restaurant;
    }

    public Vote(Integer id, LocalDate date, User user, Restaurant restaurant) {
        super(id, date);
        this.user = user;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "user=" + user +
                ", restaurant=" + restaurant +
                ", id=" + id +
                '}';
    }
}
