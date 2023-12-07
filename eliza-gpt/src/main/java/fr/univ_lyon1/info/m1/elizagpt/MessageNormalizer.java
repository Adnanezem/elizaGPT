package fr.univ_lyon1.info.m1.elizagpt;


import fr.univ_lyon1.info.m1.elizagpt.model.MessageProcessor;

/**
 * Normalize the text.
 */
public class MessageNormalizer implements MessageNormalizerInterface {

    /**
     * Turn a 1st-person sentence (Je ...) into a plural 2nd person (Vous ...).
     * The result is not capitalized to allow forming a new sentence.
     * @param text The 1st-person sentence.
     * @return The 2nd-person sentence.
     */

    @Override
    public String firstToSecondPerson(final String text) {
        String processedText = text
                .replaceAll("[Jj]e ([a-z]*)e ", "vous $1ez ");
        for (MessageProcessor.Verb v : MessageProcessor.VERBS) {
            processedText = processedText.replaceAll(
                    "[Jj]e " + v.getFirstSingular(),
                    "vous " + v.getSecondPlural());
        }
        processedText = processedText
                .replaceAll("[Jj]e ([a-z]*)s ", "vous $1ssez ")
                .replace("mon ", "votre ")
                .replace("ma ", "votre ")
                .replace("mes ", "vos ")
                .replace("moi", "vous");
        return processedText;
    }


    /**
     * Normlize the text: remove extra spaces, add a final dot if missing.
     * @param text text to normalize.
     * @return normalized text.
     */
    @Override
    public String normalize(final String text) {
        return text.replaceAll("\\s+", " ")
                .replaceAll("^\\s+", "")
                .replaceAll("\\s+$", "")
                .replaceAll("[^.!?:]$", "$0.");
    }
}
