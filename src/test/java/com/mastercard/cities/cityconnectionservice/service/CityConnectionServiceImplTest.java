package com.mastercard.cities.cityconnectionservice.service;

import com.mastercard.cities.cityconnectionservice.util.CityConnectionUtilities;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
class CityConnectionServiceImplTest {

    @MockBean
    private CityConnectionUtilities cityConnectionUtilities;

    @Autowired
    CityConnectionService cityConnectionService;

    @Test
    void citiesConnected() {
        given(this.cityConnectionUtilities.citiesConnected("Boston", "Newark")).willReturn(true);
        assertThat(cityConnectionService.citiesConnected("Boston","Newark")).isEqualTo(true);
    }

    @Test
    void citiesNotConnected() {
        given(this.cityConnectionUtilities.citiesConnected("Philadelphia", "Albany")).willReturn(false);
        assertThat(cityConnectionService.citiesConnected("Philadelphia","Albany")).isEqualTo(false);
    }
}