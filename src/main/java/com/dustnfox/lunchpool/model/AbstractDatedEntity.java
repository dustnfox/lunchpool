package com.dustnfox.lunchpool.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@MappedSuperclass
abstract class AbstractDatedEntity extends AbstractBaseEntity {

    @NotNull
    @Column(name = "date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate date;

    AbstractDatedEntity() {
        super(null);
        this.date = LocalDate.now();
    }

    AbstractDatedEntity(LocalDate date) {
        this.date = date;
    }

    AbstractDatedEntity(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
