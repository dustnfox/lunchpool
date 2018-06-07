package com.dustnfox.lunchpool.service;

import com.dustnfox.lunchpool.controller.AbstractControllerTest;
import com.dustnfox.lunchpool.model.PoolOption;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.dustnfox.lunchpool.testdata.RestaurantTestData.REST1;
import static com.dustnfox.lunchpool.testdata.RestaurantTestData.REST2;
import static com.dustnfox.lunchpool.testdata.UserTestData.ADMIN_ID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PoolServiceTest extends AbstractControllerTest {
    private static final LocalDate POOL_DATE = LocalDate.of(2018, 4, 7);
    private static final LocalDateTime POOL_DATE_TIME_BEFORE_DL =
            LocalDateTime.of(2018, 4, 7, 9, 0);
    private static final LocalDateTime POOL_DATE_TIME_AFTER_DL =
            LocalDateTime.of(2018, 4, 7, 12, 0);

    @Autowired
    private PoolService service;

    @Test
    public void getPoolResult() {
        List<PoolOption> results = service.getPoolResult(POOL_DATE);
        assertThat(results).isEqualToComparingFieldByFieldRecursively(
                Lists.newArrayList(new PoolOption(REST1, 2)));
    }

    @Test
    public void voteNotCounted() {
        service.vote(REST2.getId(), ADMIN_ID, POOL_DATE_TIME_AFTER_DL);
        List<PoolOption> results = service.getPoolResult(POOL_DATE);
        assertThat(results).isEqualToComparingFieldByFieldRecursively(
                Lists.newArrayList(new PoolOption(REST1, 2)));
    }

    @Test
    public void voteCounted() {
        service.vote(REST2.getId(), ADMIN_ID, POOL_DATE_TIME_BEFORE_DL);
        List<PoolOption> results = service.getPoolResult(POOL_DATE);
        assertThat(results).isEqualToComparingFieldByFieldRecursively(
                Lists.newArrayList(new PoolOption(REST1, 1), new PoolOption(REST2, 1)));
    }

    @Test
    public void newVote() {
        service.vote(REST1.getId(), ADMIN_ID, LocalDateTime.of(2018, 4, 8, 9, 0));
        List<PoolOption> results = service.getPoolResult(LocalDate.of(2018, 4, 8));
        assertThat(results).isEqualToComparingFieldByFieldRecursively(
                Lists.newArrayList(new PoolOption(REST1, 1)));
    }
}