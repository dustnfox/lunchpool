package ru.javawebinar.lunchpool.service;

import ru.javawebinar.lunchpool.model.Restaurant;
import ru.javawebinar.lunchpool.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> getAll();

    List<Restaurant> getAllActive();

    Restaurant get(int id);

    void delete(int id) throws NotFoundException;

    Restaurant add(Restaurant restaurant);

    Restaurant update(Restaurant restaurant, int id);
}
