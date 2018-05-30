package com.dustnfox.lunchpool.service;

import com.dustnfox.lunchpool.model.MenuEntry;
import com.dustnfox.lunchpool.testdata.RestaurantTestData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.dustnfox.lunchpool.testdata.MenuEntryTestData.*;
import static com.dustnfox.lunchpool.testdata.RestaurantTestData.REST1;
import static com.dustnfox.lunchpool.testdata.RestaurantTestData.REST1_ID;

public class MenuEntryServiceTest extends AbstractServiceTest {
    @Autowired
    private MenuEntryServiceImpl service;

    @Test
    public void get() {
        assertMatch(service.get(ME1_ID), ME_1);
    }

    @Test
    public void getAllActive() {
        List<MenuEntry> actual = service.getAll(ME_1_DATE);
        assertMatch(actual, ME_ACTIVE_LIST_FIRST_DAY);
    }

    @Test
    public void getAll() {
        List<MenuEntry> actual = service.getAllWithDeleted(ME_1_DATE);
        assertMatch(actual, ME_ALL_LIST_FIRST_DAY);
    }

    @Test
    public void delete() {
        service.delete(ME1_ID);
        assertMatch(service.getAll(ME_1_DATE), ME_2);
    }

    @Test
    public void add() {
        MenuEntry newME = new MenuEntry(ME_1_DATE, REST1, 250, "new Description");
        service.create(newME, REST1_ID);
        assertMatch(service.getAllWithDeleted(ME_1_DATE), ME_1, ME_2, ME_INACTIVE, newME);
    }

    @Test
    public void update() {
        service.update(ME_1_UPDATED, REST1_ID);
        assertMatch(service.getAllWithDeleted(ME_1_DATE), ME_1_UPDATED, ME_2, ME_INACTIVE);
    }

    @Test
    public void getWithRestaurants() {
        List<MenuEntry> entries = service.getAllWithRestaurants(ME_1_DATE);
        assertMatch(entries, ME_1, ME_2);
        RestaurantTestData.assertMatch(entries.get(0).getRestaurant(), ME_1.getRestaurant());
        RestaurantTestData.assertMatch(entries.get(1).getRestaurant(), ME_2.getRestaurant());
    }
}