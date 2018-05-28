package com.dustnfox.lunchpool.service;

import com.dustnfox.lunchpool.model.Restaurant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.dustnfox.lunchpool.testdata.RestaurantTestData.*;

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
        service.update(REST_UPDATED, REST1_ID);
        assertMatch(service.getAll(), REST_UPDATED, REST2, REST_INACTIVE);
    }
}