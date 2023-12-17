package fr.univ_lyon1.info.m1.elizagpt;

import fr.univ_lyon1.info.m1.elizagpt.controller.Controller;
import fr.univ_lyon1.info.m1.elizagpt.model.Chat;
import fr.univ_lyon1.info.m1.elizagpt.model.search.Search;
import fr.univ_lyon1.info.m1.elizagpt.view.JfxView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for the application (structure imposed by JavaFX).
 */
public class App extends Application {

    /**
     * With javafx, start() is called when the application is launched.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        Chat chat = new Chat();
        Search search = new Search();

        Controller controller = new Controller(chat, search);

        JfxView view = new JfxView(controller, stage, 600, 600);
        // Second view (uncomment to activate)
        JfxView view2 = new JfxView(controller, new Stage(), 400, 400);

        // Add the views as observers of the model
        chat.addObserver(view);
        chat.addObserver(view2);

        search.addObserver(view);
        search.addObserver(view2);


    }


    /**
     * A main method in case the user launches the application using
     * App as the main class.
     */
    public static void main(final String[] args) {
        Application.launch(args);
    }
}
