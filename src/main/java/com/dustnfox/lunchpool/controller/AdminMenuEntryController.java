package com.dustnfox.lunchpool.controller;

import com.dustnfox.lunchpool.model.MenuEntry;
import com.dustnfox.lunchpool.service.MenuEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(AdminMenuEntryController.REST_URL)
public class AdminMenuEntryController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/admin";

    private final MenuEntryService service;

    @Autowired
    public AdminMenuEntryController(MenuEntryService service) {
        this.service = service;
    }

    @GetMapping(value = "/menuentry/all/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuEntry> getAll(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return service.getAllWithDeleted(date == null ? LocalDate.now() : date);
    }

    @GetMapping(value = "/menuentry/active/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuEntry> getAllActive(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return service.getAll(date == null ? LocalDate.now() : date);
    }

    @GetMapping(value = "/menuentry/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MenuEntry get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PostMapping(value = "/restaurant/{id}/menuentry", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuEntry> createWithLocation(@Valid @RequestBody MenuEntry entry,
                                                        @PathVariable("id") int restaurantId) {
        MenuEntry created = service.create(entry, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/menuentry/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @PutMapping(value = "/restaurant/{id}/menuentry", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody MenuEntry entry, @PathVariable("id") int id) {
        service.update(entry, id);
    }
}