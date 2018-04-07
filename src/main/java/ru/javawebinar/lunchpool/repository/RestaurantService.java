package ru.javawebinar.lunchpool.repository;

import ru.javawebinar.lunchpool.model.Dish;
import ru.javawebinar.lunchpool.model.Restaurant;
import ru.javawebinar.lunchpool.util.exception.NotFoundException;

import java.time.LocalDate;

public interface RestaurantService {

    Restaurant addRestaurant(Restaurant restaurant);

    boolean deleteRestaurant(Restaurant restaurant);

    Dish addDish(Dish dish);

    Dish getDishes() throws NotFoundException;

    Dish getDishes(LocalDate date) throws NotFoundException;
}
