package ru.javawebinar.lunchpool.model;

import java.time.LocalDate;
import java.util.Set;

class User extends AbstractNamedDatedEntity {

    private final String email;

    private final String password;

    private final Set<Role> roles;

    public User(String name, String email, String password, Set<Role> roles) {
        super(name);
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(Integer id, String name, LocalDate creationDate, String email, String password, Set<Role> roles) {
        super(id, name, creationDate);
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
