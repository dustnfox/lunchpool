package com.dustnfox.lunchpool.service;

import com.dustnfox.lunchpool.model.MenuEntry;

import java.time.LocalDate;
import java.util.List;

public interface MenuEntryService {
    MenuEntry create(MenuEntry entry, int restaurantId);

    MenuEntry update(MenuEntry entry, int restaurantId);

    MenuEntry get(int id);

    List<MenuEntry> getAll(LocalDate date);

    List<MenuEntry> getAllWithDeleted(LocalDate date);

    void delete(int id);

    List<MenuEntry> getAllWithRestaurants(LocalDate date);
}
