package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.MessageListener;
import fr.univ_lyon1.info.m1.elizagpt.MessageNormalizerInterface;
import fr.univ_lyon1.info.m1.elizagpt.MessageProcessorInterface;
import fr.univ_lyon1.info.m1.elizagpt.view.JfxView;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Controller for the Eliza chatbot.
 */
public class Controller implements MessageListener {
    /**
     * The model of the application.
     */
    private final MessageProcessorInterface model;

    /**
     * The normalizer of the application.
     */
    private final MessageNormalizerInterface normalizer;
    /**
     * The view of the application.
     */
    private final JfxView view;
    private final Random random = new Random();

    /**
     * Create a new controller.
     *
     * @param model      The model of the application.
     * @param normalizer The normalizer of the application.
     * @param view       The view of the application.
     */
    public Controller(final MessageProcessorInterface model,
                      final MessageNormalizerInterface normalizer,
                      final JfxView view) {
        this.model = model;
        this.normalizer =  normalizer;
        this.view = view;
    }



    @Override
    public void onMessage(final String message) {
        processMessage(message);
    }

    /**
     * Display a message in the chat.
     *
     * @param message The message to display.
     */
    private void display(final String message) {
        view.displayChatMessage(message);
    }

    /**
     * Get the name of the user from the dialog.
     *
     * @return The name of the user, or null if not found.
     */
    private String getName() {
        return model.getName(view.getDialog());
    }

    /**
     * Process a message from the user.
     *
     * @param text The message to process.
     */
    public void processMessage(final String text) {
        String normalizedText = normalizer.normalize(text);

        Pattern pattern;
        Matcher matcher;

        // First, try to answer specifically to what the user said
        pattern = Pattern.compile(".*Je m'appelle (.*)\\.", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            display("Bonjour " + matcher.group(1) + ".");
            return;
        }
        pattern = Pattern.compile("Quel est mon nom \\?", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            if (getName() != null) {
                display("Votre nom est " + getName() + ".");
            } else {
                display("Je ne connais pas votre nom.");
            }
            return;
        }
        pattern = Pattern.compile("Qui est le plus (.*) \\?", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            display("Le plus " + matcher.group(1)
                    + " est bien sûr votre enseignant de MIF01 !");
            return;
        }
        pattern = Pattern.compile("(Je .*)\\.", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            final String startQuestion = model.pickRandom(new String[] {
                    "Pourquoi dites-vous que ",
                    "Pourquoi pensez-vous que ",
                    "Êtes-vous sûr que ",
            });
            display(startQuestion + normalizer.firstToSecondPerson(matcher.group(1)) + " ?");
            return;
        }
        // Nothing clever to say, answer randomly
        if (random.nextBoolean()) {
            display("Il faut beau aujourd'hui, vous ne trouvez pas ?");
            return;
        }
        if (random.nextBoolean()) {
            display("Je ne comprends pas.");
            return;
        }
        if (random.nextBoolean()) {
            display("Hmmm, hmm ...");
            return;
        }
        // Default answer
        if (getName() != null) {
            display("Qu'est-ce qui vous fait dire cela, " + getName() + " ?");
        } else {
            display("Qu'est-ce qui vous fait dire cela ?");
        }
    }

}
