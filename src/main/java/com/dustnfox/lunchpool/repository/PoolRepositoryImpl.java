package com.dustnfox.lunchpool.repository;

import com.dustnfox.lunchpool.model.Restaurant;

public class PoolRepositoryImpl implements PoolRepository {
    @Override
    public boolean saveRestaurant(Restaurant restaurant) {
        return false;
    }

    @Override
    public boolean enableRestaurant(int id, boolean enabled) {
        return false;
    }
}
