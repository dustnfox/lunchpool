package com.dustnfox.lunchpool.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "date"}, name = "user_date_idx")
})
public class Vote extends AbstractDatedEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;


    public Vote() {
    }


    public Vote(LocalDate date, User user, Restaurant restaurant) {
        super(date);
        this.user = user;
        this.restaurant = restaurant;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}
