package fr.univ_lyon1.info.m1.elizagpt;

import fr.univ_lyon1.info.m1.elizagpt.model.MessageProcessor;
import fr.univ_lyon1.info.m1.elizagpt.view.JfxView;
import fr.univ_lyon1.info.m1.elizagpt.controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;



/**
 * Main class for the application (structure imposed by JavaFX).
 */
public class App extends Application {

    public static final int WH = 600;

    /**
     * With javafx, start() is called when the application is launched.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        //new JfxView(stage, 600, 600);

        MessageProcessor model = new MessageProcessor();
        MessageNormalizer normalizer = new MessageNormalizer();

        JfxView view = new JfxView(stage, WH, WH);
        Controller controller = new Controller(model, normalizer, view);
        view.addMessageListener(controller);

        // Second view (uncomment to activate)
        JfxView view2 = new JfxView(new Stage(), 400, 400);
        Controller controller2 = new Controller(model, normalizer, view2);
        view2.addMessageListener(controller2);

        view.showStage();
        view2.showStage();


    }


    /**
     * A main method in case the user launches the application using
     * App as the main class.
     * @param args The command line arguments.
     */
    public static void main(final String[] args) {
        Application.launch(args);
    }


}
