package com.dustnfox.lunchpool.repository;


import com.dustnfox.lunchpool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface CrudUserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Query("UPDATE User u set u.enabled=:enabled WHERE u.id=:id")
    int setStatusForUser(@Param("id") int id, @Param("enabled") boolean enabled);

    @Transactional(readOnly = true)
    Optional<User> findByIdAndEnabled(Integer id, Boolean enabled);

    @Transactional(readOnly = true)
    List<User> findAllByOrderByEmailAscIdAsc();

    @Transactional(readOnly = true)
    List<User> findAllByEnabledOrderByEmailAscIdAsc(Boolean enabled);

    @Transactional(readOnly = true)
    User getByEmailAndEnabled(String email, Boolean enabled);
}
