package ru.javawebinar.lunchpool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.lunchpool.model.Restaurant;
import ru.javawebinar.lunchpool.service.RestaurantService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(AdminRestController.REST_URL)
public class AdminRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/admin/restaurant";

    private final RestaurantService service;

    @Autowired
    public AdminRestController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllActive() {
        return service.getAllActive();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
        Restaurant created = service.add(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        service.update(restaurant, id);
    }
}