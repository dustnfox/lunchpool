package com.dustnfox.lunchpool.testdata;

import com.dustnfox.lunchpool.model.Role;
import com.dustnfox.lunchpool.model.User;
import com.dustnfox.lunchpool.utils.JsonUtil;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;

import static com.dustnfox.lunchpool.model.AbstractBaseEntity.START_SEQ;
import static com.dustnfox.lunchpool.utils.JsonUtil.writeIgnoreProps;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class UserTestData {
    public static final int ADMIN_ID = START_SEQ + 7;
    public static final int USER_ID = START_SEQ + 8;

    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@dustnfox.com", "456",
            Role.ROLE_USER, Role.ROLE_ADMIN);
    public static final User USER = new User(USER_ID, "User", "user@dustnfox.com", "123", Role.ROLE_USER);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "meals", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("password").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(User... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "password"));
    }

    public static ResultMatcher contentJson(User expected) {
        return content().json(writeIgnoreProps(expected, "password"));
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}