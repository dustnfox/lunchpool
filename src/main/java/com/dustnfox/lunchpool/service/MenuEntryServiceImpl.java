package com.dustnfox.lunchpool.service;

import com.dustnfox.lunchpool.model.MenuEntry;
import com.dustnfox.lunchpool.model.Restaurant;
import com.dustnfox.lunchpool.repository.CrudMenuEntryRepository;
import com.dustnfox.lunchpool.repository.CrudRestaurantRepository;
import com.dustnfox.lunchpool.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class MenuEntryServiceImpl implements MenuEntryService {

    private final CrudMenuEntryRepository menuEntryRepository;
    private final CrudRestaurantRepository restaurantRepository;

    @Autowired
    public MenuEntryServiceImpl(CrudMenuEntryRepository repository, CrudRestaurantRepository restaurantRepository) {
        this.menuEntryRepository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public MenuEntry create(MenuEntry entry, int restaurantId) {
        Assert.notNull(entry, "Menu entry must not be null.");
        entry.setId(null);

        try {
            entry.setRestaurant(restaurantRepository.getOne(restaurantId));
            entry.setRestaurant(restaurantRepository.getOne(restaurantId));
            return menuEntryRepository.save(entry);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Restaurant not found id=" + restaurantId, e);
        }
    }

    @Override
    public MenuEntry update(MenuEntry entry, int restaurantId) {
        Assert.notNull(entry, "Menu entry must not be null.");
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        entry.setRestaurant(restaurant);
        return menuEntryRepository.save(entry);
    }

    @Override
    public MenuEntry get(int id) {
        return menuEntryRepository.findById(id).orElseThrow(() -> new NotFoundException("Menu entry not found id=" + id));
    }


    @Override
    public List<MenuEntry> getAll(LocalDate date) {
        return menuEntryRepository.getByDateAndEnabledOrderById(date, true);
    }

    @Override
    public List<MenuEntry> getAllWithDeleted(LocalDate date) {
        return menuEntryRepository.getByDateOrderById(date);
    }

    @Override
    public void delete(int id) {
        menuEntryRepository.setStatusForMenuEntry(id, false);
    }

    @Override
    public List<MenuEntry> getAllWithRestaurants(LocalDate date) {
        return menuEntryRepository.getWithRestaurants(date);
    }
}
