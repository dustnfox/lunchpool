package com.dustnfox.lunchpool.testdata;

import com.dustnfox.lunchpool.to.MenuEntryDTO;
import com.dustnfox.lunchpool.to.Option;
import com.dustnfox.lunchpool.to.Pool;

import java.time.LocalDate;
import java.util.Collections;

import static com.dustnfox.lunchpool.testdata.MenuEntryTestData.ME1;
import static com.dustnfox.lunchpool.testdata.MenuEntryTestData.ME2;
import static com.dustnfox.lunchpool.testdata.RestaurantTestData.REST1;

public class PoolTestData {

    public static final LocalDate POOL_DATE = LocalDate.of(2018, 4, 7);
    private static final Option OPTION1 = new Option(REST1);

    public static final Pool POOL;

    static {
        OPTION1.setMenuEntries(new MenuEntryDTO(ME1), new MenuEntryDTO(ME2));
        POOL = new Pool(POOL_DATE, Collections.singletonList(OPTION1));
    }
}
