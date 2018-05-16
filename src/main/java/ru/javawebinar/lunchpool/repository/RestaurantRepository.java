package ru.javawebinar.lunchpool.repository;

import ru.javawebinar.lunchpool.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    List<Restaurant> getAll();

    List<Restaurant> getAllActive();

    Restaurant get(int id);
}
