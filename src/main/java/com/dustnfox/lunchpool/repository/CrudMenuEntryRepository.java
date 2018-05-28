package com.dustnfox.lunchpool.repository;

import com.dustnfox.lunchpool.model.MenuEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Transactional
public interface CrudMenuEntryRepository extends JpaRepository<MenuEntry, Integer> {

    @Modifying
    @Query("UPDATE MenuEntry m set m.enabled=:enabled WHERE m.id=:id")
    int setStatusForMenuEntry(@Param("id") int id, @Param("enabled") boolean enabled);

    @Transactional(readOnly = true)
    List<MenuEntry> getByDateAndEnabledOrderById(@NotNull LocalDate date, boolean enabled);

    @Transactional(readOnly = true)
    List<MenuEntry> getByDateOrderById(@NotNull LocalDate date);
}
