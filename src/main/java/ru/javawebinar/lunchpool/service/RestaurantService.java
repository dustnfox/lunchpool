package ru.javawebinar.lunchpool.service;

import ru.javawebinar.lunchpool.model.Dish;
import ru.javawebinar.lunchpool.model.Restaurant;
import ru.javawebinar.lunchpool.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {

    List<Restaurant> getRestaurants();

    void removeRestaurant(Restaurant restaurant) throws NotFoundException;

    List<Dish> getDishes();

    List<Dish> getDishes(LocalDate date);
}
