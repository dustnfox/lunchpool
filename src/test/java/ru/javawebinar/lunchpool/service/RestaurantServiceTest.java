package ru.javawebinar.lunchpool.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.lunchpool.AbstractServiceTest;
import ru.javawebinar.lunchpool.model.Restaurant;

import java.util.List;

import static ru.javawebinar.lunchpool.testdata.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {
    @Autowired
    private RestaurantService service;

    @Test
    public void getAll() {
        List<Restaurant> actual = service.getAll();
        assertMatch(actual, REST1, REST2, REST_INACTIVE);
    }

    @Test
    public void get() {
        Restaurant actual = service.get(REST1_ID);
        assertMatch(actual, REST1);
    }

    @Test
    public void getAllActive() {
        List<Restaurant> actual = service.getAllActive();
        assertMatch(actual, REST1, REST2);
    }

    @Test
    public void delete() {
        service.delete(REST1_ID);
        assertMatch(service.getAllActive(), REST2);
    }

    @Test
    public void add() {
        Restaurant newRest = new Restaurant("new Name", "new address", "new Description");
        newRest.setId(service.add(newRest).getId());
        assertMatch(service.getAll(), REST1, REST2, REST_INACTIVE, newRest);
    }

    @Test
    public void update() {
        Restaurant updatedRestaurant = new Restaurant(REST1_ID, "updated name", REST1.getAddress(),
                REST1.getDescription());
        service.update(updatedRestaurant);
        assertMatch(service.getAll(), updatedRestaurant, REST2, REST_INACTIVE);
    }
}