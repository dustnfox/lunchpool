package com.dustnfox.lunchpool;

import com.dustnfox.lunchpool.utils.JsonUtil;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import static com.dustnfox.lunchpool.utils.JsonUtil.writeValue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


public class TestUtils {

    public static String getContent(ResultActions action) throws UnsupportedEncodingException {
        return action.andReturn().getResponse().getContentAsString();
    }

    public static ResultActions print(ResultActions action) throws UnsupportedEncodingException {
        System.out.println(getContent(action));
        return action;
    }

    public static <T> T readFromJson(ResultActions action, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValue(getContent(action), clazz);
    }

    public static <T> ResultMatcher contentJson(T expected) {
        return content().json(writeValue(expected));
    }

    public static <T> ResultMatcher contentJsonArray(T... expected) {
        return contentJson(expected);
    }

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
