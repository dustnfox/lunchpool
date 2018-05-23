package com.dustnfox.lunchpool.service;

import com.dustnfox.lunchpool.model.Restaurant;
import com.dustnfox.lunchpool.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> getAll();

    List<Restaurant> getAllActive();

    Restaurant get(int id);

    void delete(int id) throws NotFoundException;

    Restaurant add(Restaurant restaurant);

    Restaurant update(Restaurant restaurant, int id);
}
