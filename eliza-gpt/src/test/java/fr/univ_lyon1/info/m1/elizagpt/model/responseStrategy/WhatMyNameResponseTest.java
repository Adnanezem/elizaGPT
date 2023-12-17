package fr.univ_lyon1.info.m1.elizagpt.model.responseStrategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class WhatMyNameResponseTest {

    private WhatMyNameResponse whatMyNameResponse;

    @BeforeEach
    void setUp() {
        whatMyNameResponse = new WhatMyNameResponse();
    }

    @Test
    void shouldGenerateCorrectResponseForNameQuery() {
        String result = whatMyNameResponse.generateResponse("User", "Quel est mon nom ?");
        assertThat(result, is(equalTo("Votre nom est User.")));
    }

    @Test
    void shouldReturnNullForNonNameQuery() {
        String result = whatMyNameResponse.generateResponse("User", "Bonjour.");
        assertThat(result, is(nullValue()));
    }

    @Test
    void shouldReturnUnknownNameResponseWhenNameIsNull() {
        String result = whatMyNameResponse.generateResponse(null, "Quel est mon nom ?");
        assertThat(result, is(equalTo("Je ne connais pas votre nom.")));
    }
}
