package fr.univ_lyon1.info.m1.elizagpt.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MessageTest {

    private Message message;

    @BeforeEach
    void setUp() {
        message = new Message("User", "Hello, my name is John.");
    }

    @Test
    void shouldReturnCorrectAuthor() {
        assertThat(message.getAuthor(), is(equalTo("User")));
    }

    @Test
    void shouldReturnCorrectMessage() {
        assertThat(message.getMessage(), is(equalTo("Hello, my name is John.")));
    }

    @Test
    void shouldNormalizeMessageCorrectly() {
        assertThat(message.normalize(), is(equalTo("Hello, my name is John.")));
    }

    @Test
    void shouldNormalizeMessageWithExtraSpacesCorrectly() {
        message.setMessage("  Hello,   my name is   John.  ");
        assertThat(message.normalize(), is(equalTo("Hello, my name is John.")));
    }

    @Test
    void shouldNormalizeMessageWithoutFinalDotCorrectly() {
        message.setMessage("Hello, my name is John");
        assertThat(message.normalize(), is(equalTo("Hello, my name is John.")));
    }

    @Test
    void shouldNotAddFinalDotToIntegerMessage() {
        message.setMessage("12345");
        assertThat(message.normalize(), is(equalTo("12345")));
    }

    @Test
    void shouldReturnBotResponse() {
        String response = message.botResponse("John");
        assertThat(response, anyOf(
                is(equalTo("Il faut beau aujourd'hui, vous ne trouvez pas ?")),
                is(equalTo("Je ne comprends pas.")),
                is(equalTo("Hmmm, hmm ...")),
                is(equalTo("Qu'est-ce qui vous fait dire cela, John ?"))
        ));
    }

    @Test
    void shouldReturnBotResponseWithoutName() {
        String response = message.botResponse(null);
        assertThat(response, anyOf(
                is(equalTo("Il faut beau aujourd'hui, vous ne trouvez pas ?")),
                is(equalTo("Je ne comprends pas.")),
                is(equalTo("Hmmm, hmm ...")),
                is(equalTo("Qu'est-ce qui vous fait dire cela ?"))
        ));
    }
}
