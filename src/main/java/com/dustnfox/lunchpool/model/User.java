package com.dustnfox.lunchpool.model;

import java.time.LocalDate;
import java.util.Set;

class User extends AbstractNamedEntity {

    private final String email;

    private final String password;

    private final Set<Role> roles;

    private boolean enabled = true;

    public User(String name, String email, String password, Set<Role> roles) {
        super(null, name);
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(Integer id, String name, LocalDate creationDate, String email, String password, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
