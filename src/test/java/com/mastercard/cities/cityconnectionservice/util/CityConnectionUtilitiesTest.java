package com.mastercard.cities.cityconnectionservice.util;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class CityConnectionUtilitiesTest {

    @Autowired
    CityConnectionUtilities cityConnectionUtilities;

    @Test
    void cityConnectionMapIsNotNull() {
        assertThat(cityConnectionUtilities.cityConnectionCache).isNotNull();
    }

    @Test
    void citiesConnected() {
        assertThat(cityConnectionUtilities.citiesConnected("Boston", "Boston")).isEqualTo(true);
        assertThat(cityConnectionUtilities.citiesConnected("Boston", "Newark")).isEqualTo(true);
        assertThat(cityConnectionUtilities.citiesConnected("Newark", "Boston")).isEqualTo(true);
        assertThat(cityConnectionUtilities.citiesConnected("Boston", "Philadelphia")).isEqualTo(true);
        assertThat(cityConnectionUtilities.citiesConnected("Philadelphia", "Boston")).isEqualTo(true);
    }

    @Test
    void citiesNotConnected() {
        assertThat(cityConnectionUtilities.citiesConnected(null, "someInvalidCity")).isEqualTo(false);
        assertThat(cityConnectionUtilities.citiesConnected("someInvalidCity", null)).isEqualTo(false);
        assertThat(cityConnectionUtilities.citiesConnected(null, null)).isEqualTo(false);
        assertThat(cityConnectionUtilities.citiesConnected("", "someInvalidCity")).isEqualTo(false);
        assertThat(cityConnectionUtilities.citiesConnected("someInvalidCity", "someInvalidCity2")).isEqualTo(false);
        assertThat(cityConnectionUtilities.citiesConnected("Philadelphia", "Albany")).isEqualTo(false);
        assertThat(cityConnectionUtilities.citiesConnected("Albany", "Philadelphia")).isEqualTo(false);
    }

}