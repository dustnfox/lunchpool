package com.dustnfox.lunchpool.model;

public class PoolOption {
    private final Restaurant restaurant;
    private final long votes;

    public PoolOption(Restaurant restaurant, long votes) {
        this.restaurant = restaurant;
        this.votes = votes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public long getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "PoolOption{" +
                "restaurant=" + restaurant +
                ", votes=" + votes +
                '}';
    }
}
