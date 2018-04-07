package ru.javawebinar.lunchpool.model;

import java.time.LocalDate;

abstract class AbstractNamedDatedEntity extends AbstractNamedEntity {

    private final LocalDate creationDate;

    AbstractNamedDatedEntity(String name) {
        super(null, name);
        this.creationDate = LocalDate.now();
    }

    AbstractNamedDatedEntity(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    AbstractNamedDatedEntity(Integer id, String name, LocalDate creationDate) {
        super(id, name);
        this.creationDate = creationDate;
    }
}
