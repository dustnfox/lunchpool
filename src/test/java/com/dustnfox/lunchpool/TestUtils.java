package com.dustnfox.lunchpool;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class TestUtils {
    public static <T> void assertMatch(List<String> ignoredFields, T actual, T expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, ignoredFields.toArray(new String[0]));
    }

    @SafeVarargs
    public static <T> void assertMatchCollection(List<String> ignoredFields, Iterable<T> actual, T... expected) {
        assertMatchCollection(ignoredFields, actual, Arrays.asList(expected));
    }

    private static <T> void assertMatchCollection(List<String> ignoredFields, Iterable<T> actual, Iterable<T> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields(ignoredFields.toArray(new String[0])).isEqualTo(expected);
    }
}
