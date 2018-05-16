package ru.javawebinar.lunchpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.lunchpool.model.Restaurant;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Override
    @Modifying
    @Transactional
    Restaurant save(Restaurant restaurant);

    @Modifying
    @Transactional
    @Query("UPDATE Restaurant r set r.enabled=:enabled WHERE r.id=:id")
    int setStatusForRestaurant(@Param("id") int id, @Param("enabled") boolean enabled);

    List<Restaurant> findAllByOrderById();

    Restaurant getById(int id);

    List<Restaurant> findAllByEnabledOrderByIdAsc(boolean enabled);
}
