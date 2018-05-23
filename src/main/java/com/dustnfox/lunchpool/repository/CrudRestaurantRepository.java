package com.dustnfox.lunchpool.repository;

import com.dustnfox.lunchpool.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Modifying
    @Query("UPDATE Restaurant r set r.enabled=:enabled WHERE r.id=:id")
    int setStatusForRestaurant(@Param("id") int id, @Param("enabled") boolean enabled);

    @Transactional(readOnly = true)
    List<Restaurant> findAllByOrderById();

    @Transactional(readOnly = true)
    Restaurant getById(int id);

    @Transactional(readOnly = true)
    List<Restaurant> findAllByEnabledOrderByIdAsc(boolean enabled);
}
