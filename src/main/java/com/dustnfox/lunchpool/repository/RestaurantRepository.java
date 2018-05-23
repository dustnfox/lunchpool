package com.dustnfox.lunchpool.repository;

import com.dustnfox.lunchpool.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    List<Restaurant> getAll();

    List<Restaurant> getAllActive();

    Restaurant get(int id);
}
