package com.dustnfox.lunchpool.service;

import com.dustnfox.lunchpool.model.PoolOption;
import com.dustnfox.lunchpool.model.Restaurant;
import com.dustnfox.lunchpool.model.User;
import com.dustnfox.lunchpool.model.Vote;
import com.dustnfox.lunchpool.repository.CrudPoolRepository;
import com.dustnfox.lunchpool.repository.CrudRestaurantRepository;
import com.dustnfox.lunchpool.repository.CrudUserRepository;
import com.dustnfox.lunchpool.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
public class PoolService {
    private static LocalTime DEADLINE_TIME = LocalTime.of(11, 0);

    @Autowired
    private CrudRestaurantRepository restaurantRepository;
    @Autowired
    private CrudUserRepository userRepository;
    @Autowired
    private CrudPoolRepository poolRepository;


    public List<PoolOption> getPoolResult(LocalDate date) {
        return poolRepository.getPoolResult(date);
    }

    public List<PoolOption> getPoolResult() {
        return poolRepository.getPoolResult(LocalDate.now());
    }


    public void vote(int choiceId, int userId) {
        Vote vote = poolRepository.findFirstByDateAndUserId(LocalDate.now(), userId);
        try {
            if (vote == null) {
                Restaurant restaurant = restaurantRepository.getOne(choiceId);
                User user = userRepository.getOne(userId);
                poolRepository.save(new Vote(LocalDate.now(), user, restaurant));
            } else if (LocalTime.now().isBefore(DEADLINE_TIME)) {
                vote.setRestaurant(restaurantRepository.getOne(choiceId));
                poolRepository.save(vote);
            }
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Entity not found", e);
        }
    }
}
