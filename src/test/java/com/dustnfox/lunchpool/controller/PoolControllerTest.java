package com.dustnfox.lunchpool.controller;

import com.dustnfox.lunchpool.TestUtils;
import com.dustnfox.lunchpool.model.PoolOption;
import com.dustnfox.lunchpool.service.PoolService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;

import static com.dustnfox.lunchpool.testdata.PoolTestData.POOL;
import static com.dustnfox.lunchpool.testdata.PoolTestData.POOL_DATE;
import static com.dustnfox.lunchpool.testdata.RestaurantTestData.REST1;
import static com.dustnfox.lunchpool.testdata.RestaurantTestData.REST1_ID;
import static com.dustnfox.lunchpool.utils.JsonUtil.contentJson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PoolControllerTest extends AbstractControllerTest {
    private static final String REST_URL = PoolController.REST_URL + '/';

    @Autowired
    private PoolService service;

    @Test
    public void getCurrentPool() throws Exception {
        TestUtils.print(mockMvc.perform(get(REST_URL + "pool/" + POOL_DATE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(POOL)));
    }

    @Test
    public void vote() throws Exception {
        TestUtils.print(mockMvc.perform(put(REST_URL + "vote/" + REST1_ID))
                .andDo(print())
                .andExpect(status().isOk()));

        List<PoolOption> results = service.getPoolResult();
        assertThat(results).isEqualToComparingFieldByFieldRecursively(
                Lists.newArrayList(new PoolOption(REST1, 1)));
    }
}