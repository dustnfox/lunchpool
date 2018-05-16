package ru.javawebinar.lunchpool.controller;

import ru.javawebinar.lunchpool.to.MenuEntry;
import ru.javawebinar.lunchpool.to.RestrauntVotes;

import java.time.LocalDate;
import java.util.List;

public interface AdminRestController {
    void addMenuEntry(MenuEntry entry, LocalDate date, int restaurnatId);

    void deleteMenuEntry(int id);

    void addRestaurnat(String name, String description, String address);

    void updateRestaurant();

    void deleteRestaurant(int id);

    List<RestrauntVotes> getPoolResult(LocalDate date);
}
