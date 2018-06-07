package com.dustnfox.lunchpool.service;

import com.dustnfox.lunchpool.model.Restaurant;
import com.dustnfox.lunchpool.repository.CrudRestaurantRepository;
import com.dustnfox.lunchpool.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RestaurantService {

    private final CrudRestaurantRepository repository;

    @Autowired
    public RestaurantService(CrudRestaurantRepository repository) {
        this.repository = repository;
    }


    public List<Restaurant> getAll() {
        return repository.findAllByOrderById();
    }


    public List<Restaurant> getAllActive() {
        return repository.findAllByEnabledOrderByIdAsc(true);
    }


    public Restaurant get(int id) {
        Restaurant restaurant = repository.getById(id);
        if (restaurant == null) {
            throw new NotFoundException("Restaurant with id=" + id + " not found");
        }
        return restaurant;
    }


    public void delete(int id) throws NotFoundException {
        repository.setStatusForRestaurant(id, false);
    }


    public Restaurant add(Restaurant restaurant) {
        if (Objects.nonNull(restaurant.getId())) {
            throw new IllegalArgumentException("ID must be null in add operation.");
        }
        return repository.save(restaurant);
    }


    public Restaurant update(Restaurant restaurant, int id) {
        if (Objects.isNull(restaurant.getId())) {
            throw new IllegalArgumentException("ID has to be null in update operation.");
        }
        return repository.save(restaurant);
    }
}
