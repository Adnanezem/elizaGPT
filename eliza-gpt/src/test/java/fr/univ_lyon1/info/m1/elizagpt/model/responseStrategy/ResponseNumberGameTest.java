package fr.univ_lyon1.info.m1.elizagpt.model.responseStrategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ResponseNumberGameTest {

    private ResponseNumberGame responseNumberGame;

    @BeforeEach
    void setUp() {
        responseNumberGame = new ResponseNumberGame();
    }

    @Test
    void shouldStartGameWhenGameKeywordIsPresent() {
        String result = responseNumberGame.generateResponse("User", "Jeu");
        assertThat(result,
                is(equalTo("D'accord, je vais penser à "
                        + "un nombre entre 1 et 100, à vous de le deviner !")));
    }

    @Test
    void shouldReturnNullWhenGameKeywordIsNotPresent() {
        String result = responseNumberGame.generateResponse("User", "Bonjour");
        assertThat(result, is(nullValue()));
    }

    @Test
    void shouldReturnCorrectMessageWhenGuessIsTooSmall() {
        responseNumberGame.generateResponse("User", "Jeu");
        String result = responseNumberGame.generateResponse("User", "1");
        assertThat(result,
                is(equalTo("Le numéro que vous avez deviné est trop petit. Essayez encore !")));
    }

    @Test
    void shouldReturnCorrectMessageWhenGuessIsTooLarge() {
        responseNumberGame.generateResponse("User", "Jeu");
        String result = responseNumberGame.generateResponse("User", "100");
        assertThat(result,
                is(equalTo("Le numéro que vous avez deviné est trop grand. Essayez encore !")));
    }

    @Test
    void shouldReturnCorrectMessageWhenGuessIsNotANumber() {
        responseNumberGame.generateResponse("User", "Jeu");
        String result = responseNumberGame.generateResponse("User", "abc");
        assertThat(result,
                is(equalTo("Je n'ai pas compris votre réponse. Veuillez entrer un nombre.")));
    }
}
