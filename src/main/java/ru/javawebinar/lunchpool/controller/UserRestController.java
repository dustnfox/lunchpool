package ru.javawebinar.lunchpool.controller;

import ru.javawebinar.lunchpool.model.Restaurant;
import ru.javawebinar.lunchpool.to.MenuEntry;

import java.util.List;

public interface UserRestController {
    Restaurant getRestaurant(int id);

    List<MenuEntry> getMenu(int restaurantId);

    List<Integer> getActivePool();

    void vote(int restaurantId);
}
