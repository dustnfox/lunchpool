package com.dustnfox.lunchpool.repository;

import com.dustnfox.lunchpool.model.Restaurant;

interface PoolRepository {

    boolean saveRestaurant(Restaurant restaurant);

    boolean enableRestaurant(int id, boolean enabled);
}
