package com.dustnfox.lunchpool.controller;

import com.dustnfox.lunchpool.AuthorizedUser;
import com.dustnfox.lunchpool.model.MenuEntry;
import com.dustnfox.lunchpool.service.MenuEntryService;
import com.dustnfox.lunchpool.service.PoolService;
import com.dustnfox.lunchpool.to.Pool;
import com.dustnfox.lunchpool.util.DtoConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(PoolController.REST_URL)
public class PoolController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/user";

    private final PoolService poolService;
    private final MenuEntryService menuEntryService;


    @Autowired
    public PoolController(PoolService poolService, MenuEntryService menuEntryService) {
        this.poolService = poolService;
        this.menuEntryService = menuEntryService;
    }

    @GetMapping(value = "/pool", produces = MediaType.APPLICATION_JSON_VALUE)
    public Pool getCurrentPool() {
        List<MenuEntry> menu = menuEntryService.getAllWithRestaurants();
        return DtoConvertor.convertToPool(menu);
    }

    @GetMapping(value = "/pool/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Pool getCurrentPool(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<MenuEntry> menu = menuEntryService.getAllWithRestaurants(date);
        return DtoConvertor.convertToPool(menu);
    }

    @PutMapping(value = "/vote/{id}")
    public void vote(@PathVariable("id") int id) {
        poolService.vote(id, AuthorizedUser.id());
    }
}
