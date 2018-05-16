package ru.javawebinar.lunchpool.repository;

import ru.javawebinar.lunchpool.model.Restaurant;

public interface PoolRepository {

    boolean saveRestaurant(Restaurant restaurant);

    boolean enableRestaurant(int id, boolean enabled);
}
