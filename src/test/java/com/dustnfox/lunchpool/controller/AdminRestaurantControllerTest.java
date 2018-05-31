package com.dustnfox.lunchpool.controller;

import com.dustnfox.lunchpool.model.Restaurant;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.dustnfox.lunchpool.testdata.RestaurantTestData.*;
import static com.dustnfox.lunchpool.utils.JsonUtil.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminRestaurantControllerTest extends AbstractControllerTest {
    private static final String REST_URL = AdminRestaurantController.REST_URL + '/';

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(REST1, REST2, REST_INACTIVE));
    }

    @Test
    public void getAllActive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "active"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(REST1, REST2));
    }

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + REST1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(REST1));
    }

    @Test
    public void createWithLocation() throws Exception {
        Restaurant expected = new Restaurant("new name", "new address", "new description");
        String result = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(expected)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Restaurant restaurant = readValue(result, Restaurant.class);
        expected.setId(restaurant.getId());
        assertMatch(restaurant, expected);

        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(REST1, REST2, REST_INACTIVE, expected));
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + REST1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "active"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(REST2));
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + REST1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(REST_UPDATED)))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "active"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(REST_UPDATED, REST2));
    }
}