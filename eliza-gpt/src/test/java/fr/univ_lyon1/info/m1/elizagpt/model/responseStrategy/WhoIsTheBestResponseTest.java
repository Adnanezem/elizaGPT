package fr.univ_lyon1.info.m1.elizagpt.model.responseStrategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class WhoIsTheBestResponseTest {

    private WhoIsTheBestResponse whoIsTheBestResponse;

    @BeforeEach
    void setUp() {
        whoIsTheBestResponse = new WhoIsTheBestResponse();
    }

    @Test
    void shouldGenerateCorrectResponseForBestQuery() {
        String result = whoIsTheBestResponse.generateResponse("User", "Qui est le plus fort ?");
        assertThat(result, is(equalTo("Le plus fort est bien sûr votre enseignant de MIF01 !")));
    }

    @Test
    void shouldReturnNullForNonBestQuery() {
        String result = whoIsTheBestResponse.generateResponse("User", "Bonjour.");
        assertThat(result, is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenNameIsNull() {
        String result = whoIsTheBestResponse.generateResponse(null, "Qui est le plus fort ?");
        assertThat(result, is(equalTo("Le plus fort est bien sûr votre enseignant de MIF01 !")));
    }
}
