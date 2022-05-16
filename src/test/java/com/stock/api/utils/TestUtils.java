package com.stock.api.utils;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestUtils {

    private MockMvc mockMvc;

    public TestUtils(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public MvcResult testCreateStock(String content) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/stocks")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        return result;
    }

    public MvcResult testGetStocks(String page, String pageSize) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/stocks")
                        .queryParam("page", page)
                        .queryParam("pageSize", pageSize)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        return result;
    }

    public MvcResult testGetStock(long id) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(String.format("/api/stocks/%d", id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        return result;
    }

    public MvcResult testUpdateStock(long id, String value) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch(String.format("/api/stocks/%d", id))
                        .content(value)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        return result;
    }

    public MvcResult testDeleteStock(long id) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/api/stocks/%d", id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        return result;
    }
}
