package com.dustnfox.lunchpool.repository;

import com.dustnfox.lunchpool.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final CrudRestaurantRepository crudRestaurantRepository;

    @Autowired
    public RestaurantRepositoryImpl(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return crudRestaurantRepository.setStatusForRestaurant(id, false) == 1;
    }

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepository.getById(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAllByOrderById();
    }

    @Override
    public List<Restaurant> getAllActive() {
        return crudRestaurantRepository.findAllByEnabledOrderByIdAsc(true);
    }
}
