package com.dustnfox.lunchpool.testdata;

import com.dustnfox.lunchpool.model.Restaurant;

import java.util.Arrays;

import static com.dustnfox.lunchpool.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {
    public static final int REST1_ID = START_SEQ;
    private static final int REST2_ID = START_SEQ + 1;
    private static final int REST_INACTIVE_ID = START_SEQ + 2;

    public static final Restaurant REST1 = new Restaurant(REST1_ID, "Roll with the dishes", "23, Baker st.",
            "Japanese cousins");
    public static final Restaurant REST2 = new Restaurant(REST2_ID, "Wild boar", "13, Middle of nowhere st.",
            "Medieval classical cousins");
    public static final Restaurant REST_INACTIVE = new Restaurant(REST_INACTIVE_ID, "inactive", "",
            "");
    public static final Restaurant REST_UPDATED = new Restaurant(REST1_ID, "updated name", REST1.getAddress(),
            REST1.getDescription());

    static {
        REST_INACTIVE.setEnabled(false);
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    private static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields().isEqualTo(expected);
    }
}
