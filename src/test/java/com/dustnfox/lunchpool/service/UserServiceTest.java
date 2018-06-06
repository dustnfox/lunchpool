package com.dustnfox.lunchpool.service;

import com.dustnfox.lunchpool.model.Role;
import com.dustnfox.lunchpool.model.User;
import com.dustnfox.lunchpool.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.Collections;
import java.util.List;

import static com.dustnfox.lunchpool.testdata.UserTestData.*;


public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Test
    public void create() {
        User newUser = new User(null, "New", "new@gmail.com", "newPass",
                Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, newUser, USER);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() {
        service.create(new User(null, "Duplicate", USER.getEmail(), "newPass", Role.ROLE_USER));
    }

    @Test
    public void delete() {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() {
        service.delete(1);
    }

    @Test
    public void get() {
        User user = service.get(ADMIN_ID);
        assertMatch(user, ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(1);
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail(ADMIN.getEmail());
        assertMatch(user, ADMIN);
    }

    @Test
    public void update() {
        User updated = new User(USER.getId(), USER.getName(), USER.getEmail(), USER.getPassword(), USER.getRoles());
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void getAll() {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER);
    }
}