package fr.univ_lyon1.info.m1.elizagpt.model.responseStrategy;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ResponseStrategiesTest {

    @Test
    void shouldReturnCorrectResponseStrategies() {
        ArrayList<ResponseStrategy> responseStrategies = ResponseStrategies.getResponseStrategies();
        assertThat(responseStrategies, hasSize(6));
        assertThat(responseStrategies.get(0), is(instanceOf(GoodbyeResponse.class)));
        assertThat(responseStrategies.get(1), is(instanceOf(NameResponse.class)));
        assertThat(responseStrategies.get(2), is(instanceOf(WhoIsTheBestResponse.class)));
        assertThat(responseStrategies.get(3), is(instanceOf(WhatMyNameResponse.class)));
        assertThat(responseStrategies.get(4), is(instanceOf(ResponseNumberGame.class)));
        assertThat(responseStrategies.get(5), is(instanceOf(ConjugaisonResponse.class)));
    }
}
