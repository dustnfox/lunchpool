package com.dustnfox.lunchpool.util;

import com.dustnfox.lunchpool.model.MenuEntry;
import com.dustnfox.lunchpool.to.MenuEntryDTO;
import com.dustnfox.lunchpool.to.Option;
import com.dustnfox.lunchpool.to.Pool;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DtoConvertor {
    public static Pool convertToPool(List<MenuEntry> entryList) {
        LocalDate date = entryList.size() == 0 ? LocalDate.now() : entryList.get(0).getDate();
        Map<Integer, Option> optionMap = new HashMap<>();
        entryList.forEach(e -> optionMap.computeIfAbsent(e.getRestaurant().getId(),
                (k) -> new Option(e.getRestaurant())).setMenuEntries(new MenuEntryDTO(e)));
        return new Pool(date, optionMap.values());
    }
}
