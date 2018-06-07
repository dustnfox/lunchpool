package com.dustnfox.lunchpool.controller;

import com.dustnfox.lunchpool.model.MenuEntry;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.dustnfox.lunchpool.testdata.MenuEntryTestData.*;
import static com.dustnfox.lunchpool.testdata.RestaurantTestData.REST1;
import static com.dustnfox.lunchpool.testdata.RestaurantTestData.REST1_ID;
import static com.dustnfox.lunchpool.utils.JsonUtil.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuEntryControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MenuEntryController.REST_URL + '/';
    private static final String REST_ME_URL = REST_URL + "menuentry/";

    @Test
    public void getAllWithDeleted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_ME_URL + "all/" + ME1_DATE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(ME1, ME2, ME_INACTIVE));
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_ME_URL + "active/" + ME1_DATE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(ME1, ME2));
    }

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_ME_URL + ME1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ME1));
    }

    @Test
    public void createWithLocation() throws Exception {
        MenuEntry expected = new MenuEntry(ME3.getDate(), REST1, 100, "new name");
        String result = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL + "/restaurant/" + REST1_ID + "/menuentry")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(expected)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        MenuEntry menuEntry = readValue(result, MenuEntry.class);
        expected.setId(menuEntry.getId());
        assertMatch(menuEntry, expected);

        mockMvc.perform(MockMvcRequestBuilders.get(REST_ME_URL + "active/" + ME3.getDate()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(ME3, menuEntry));
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_ME_URL + ME1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get(REST_ME_URL + "active/" + ME1_DATE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(ME2));
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + "/restaurant/" + REST1_ID + "/menuentry")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(ME1_UPDATED)))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get(REST_ME_URL + "active/" + ME1_DATE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(ME1_UPDATED, ME2));
    }
}