package com.dustnfox.lunchpool.service;

import com.dustnfox.lunchpool.controller.AbstractControllerTest;
import com.dustnfox.lunchpool.model.PoolOption;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static com.dustnfox.lunchpool.testdata.RestaurantTestData.REST1;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PoolServiceImplTest extends AbstractControllerTest {
    private static final LocalDate POOL_DATE = LocalDate.of(2018, 4, 7);

    @Autowired
    private PoolService service;

    @Test
    public void getPoolResult() {
        List<PoolOption> results = service.getPoolResult(POOL_DATE);
        assertThat(results).isEqualToComparingFieldByFieldRecursively(
                Lists.newArrayList(new PoolOption(REST1, 2)));
    }

    @Test
    public void vote() {

    }
}