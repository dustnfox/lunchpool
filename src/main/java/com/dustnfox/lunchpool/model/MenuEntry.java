package com.dustnfox.lunchpool.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "menu_entries", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"rest_id", "date", "name"}, name = "menu_entry_unique_date_rest_name_idx")})
public class MenuEntry extends AbstractDatedEntity {
    @Column(name = "price")
    @NotNull
    private Integer priceInCents;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @NotNull
    private String name;

    public MenuEntry() {
    }

    public MenuEntry(LocalDate date, Restaurant restaurant, Integer priceInCents, String name) {
        super(date);
        this.priceInCents = priceInCents;
        this.restaurant = restaurant;
        this.name = name;
    }

    public MenuEntry(Integer id, Restaurant restaurant, LocalDate date, Integer priceInCents, String name) {
        super(id, date);
        this.priceInCents = priceInCents;
        this.restaurant = restaurant;
        this.name = name;
    }

    public Integer getPriceInCents() {
        return priceInCents;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public String getName() {
        return name;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "MenuEntry{" +
                "priceInCents=" + priceInCents +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", enabled=" + enabled +
                '}';
    }
}
