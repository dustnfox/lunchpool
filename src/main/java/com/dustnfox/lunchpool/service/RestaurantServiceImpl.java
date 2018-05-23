package com.dustnfox.lunchpool.service;

import com.dustnfox.lunchpool.model.Restaurant;
import com.dustnfox.lunchpool.repository.RestaurantRepository;
import com.dustnfox.lunchpool.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Restaurant> getAllActive() {
        return repository.getAllActive();
    }

    @Override
    public Restaurant get(int id) {
        Restaurant restaurant = repository.get(id);
        if (restaurant == null) {
            throw new NotFoundException("Restaurant with id=" + id + " not found");
        }
        return restaurant;
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.delete(id);
    }

    @Override
    public Restaurant add(Restaurant restaurant) {
        if (Objects.nonNull(restaurant.getId())) {
            throw new IllegalArgumentException("ID must be null in add operation.");
        }
        return repository.save(restaurant);
    }

    @Override
    public Restaurant update(Restaurant restaurant, int id) {
        if (Objects.isNull(restaurant.getId())) {
            throw new IllegalArgumentException("ID has to be null in update operation.");
        }
        return repository.save(restaurant);
    }
}
