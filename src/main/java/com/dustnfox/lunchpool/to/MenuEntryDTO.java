package com.dustnfox.lunchpool.to;

import com.dustnfox.lunchpool.model.MenuEntry;

import java.time.LocalDate;

public class MenuEntryDTO {
    String name;
    int priceInCents;

    public MenuEntryDTO(MenuEntry entry) {
        name = entry.getName();
        priceInCents = entry.getPriceInCents();
    }

    public String getName() {
        return name;
    }

    public long getPriceInCents() {
        return priceInCents;
    }

    public static MenuEntry getFromDTO(MenuEntryDTO dto, LocalDate date) {
        return new MenuEntry(date, null, dto.priceInCents, dto.getName());
    }

    public static MenuEntry getFromDTO(MenuEntryDTO dto, LocalDate date, int id) {
        MenuEntry entry = getFromDTO(dto, date);
        entry.setId(id);
        return entry;
    }
}
