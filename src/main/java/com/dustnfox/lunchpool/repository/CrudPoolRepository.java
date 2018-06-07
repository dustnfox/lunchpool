package com.dustnfox.lunchpool.repository;

import com.dustnfox.lunchpool.model.PoolOption;
import com.dustnfox.lunchpool.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudPoolRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT new com.dustnfox.lunchpool.model.PoolOption(v.restaurant, COUNT(v)) FROM Vote v " +
            "WHERE v.date=:date GROUP BY v.restaurant")
    List<PoolOption> getPoolResult(@Param("date") LocalDate date);

    Vote findFirstByDateAndUserId(LocalDate date, int userId);
}
