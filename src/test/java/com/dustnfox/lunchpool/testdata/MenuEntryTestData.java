package com.dustnfox.lunchpool.testdata;

import com.dustnfox.lunchpool.model.MenuEntry;

import java.time.LocalDate;
import java.util.Arrays;

import static com.dustnfox.lunchpool.model.AbstractBaseEntity.START_SEQ;
import static com.dustnfox.lunchpool.testdata.RestaurantTestData.REST1;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuEntryTestData {
    public static final int ME1_ID = START_SEQ + 3;
    private static final int ME2_ID = START_SEQ + 4;
    private static final int ME3_ID = START_SEQ + 5;
    private static final int ME_INACTIVE_ID = START_SEQ + 6;

    public static final MenuEntry ME1 = new MenuEntry(ME1_ID, REST1, LocalDate.of(2018, 4, 7), 899, "Philadelphia roll");
    public static final MenuEntry ME2 = new MenuEntry(ME2_ID, REST1, LocalDate.of(2018, 4, 7), 655, "Tuna fish susi");
    public static final MenuEntry ME3 = new MenuEntry(ME3_ID, REST1, LocalDate.of(2018, 4, 8), 1250, "Beef steak");
    public static final MenuEntry ME_INACTIVE = new MenuEntry(ME_INACTIVE_ID, REST1, LocalDate.of(2018, 4, 7), 125, "Chicken soup");
    public static final MenuEntry ME1_UPDATED = new MenuEntry(ME1_ID, REST1, ME1.getDate(), ME1.getPriceInCents(), "New updated Chicken soup");

    public static final MenuEntry[] ME_ALL_LIST_FIRST_DAY = new MenuEntry[]{ME1, ME2, ME_INACTIVE};
    public static final MenuEntry[] ME_ACTIVE_LIST_FIRST_DAY = new MenuEntry[]{ME1, ME2};
    public static final LocalDate ME1_DATE = ME1.getDate();

    static {
        ME_INACTIVE.setEnabled(false);
    }

    public static void assertMatch(MenuEntry actual, MenuEntry expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<MenuEntry> actual, MenuEntry... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    private static void assertMatch(Iterable<MenuEntry> actual, Iterable<MenuEntry> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }
}
