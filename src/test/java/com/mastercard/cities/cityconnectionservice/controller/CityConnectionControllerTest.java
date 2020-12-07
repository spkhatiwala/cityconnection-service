package com.mastercard.cities.cityconnectionservice.controller;

import com.mastercard.cities.cityconnectionservice.service.CityConnectionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CityConnectionController.class)
class CityConnectionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityConnectionServiceImpl service;

    @Test
    public void cityConnectionControllerShouldReturnNo() throws Exception {
        when(service.citiesConnected("Philadelphia","Albany")).thenReturn(false);
        this.mockMvc.perform(get("/connected?origin=Philadelphia&destination=Albany")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("no")));
    }

    @Test
    public void cityConnectionControllerShouldReturnYes() throws Exception {
        when(service.citiesConnected("Boston","Newark")).thenReturn(true);
        this.mockMvc.perform(get("/connected?origin=Boston&destination=Newark")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(equalTo("yes")));
    }
}