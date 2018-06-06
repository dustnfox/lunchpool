package com.dustnfox.lunchpool.service;

import com.dustnfox.lunchpool.model.User;
import com.dustnfox.lunchpool.repository.CrudUserRepository;
import com.dustnfox.lunchpool.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.dustnfox.lunchpool.util.ValidationUtil.checkNotFound;
import static com.dustnfox.lunchpool.util.ValidationUtil.checkNotFoundWithId;


@Service
public class UserService {

    private final CrudUserRepository repository;

    @Autowired
    public UserService(CrudUserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        user.setId(null);
        return repository.save(user);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.setStatusForUser(id, false) != 0, id);
    }

    public void enable(int id, boolean enabled) {
        checkNotFoundWithId(repository.setStatusForUser(id, true), id);
    }

    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.findByIdAndEnabled(id, true).orElse(null), id);
    }

    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmailAndEnabled(email, true), "email=" + email);
    }

    public List<User> getAll() {
        return repository.findAllByEnabledOrderByEmailAscIdAsc(true);
    }

    public List<User> getAllWithDeleted() {
        return repository.findAllByOrderByEmailAscIdAsc();
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user), user.getId());
    }
}