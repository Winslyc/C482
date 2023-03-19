package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.*;

/**
 * Main Class that provides the launcher for the inventory application
 *
 * The java docs are located in the Folder JavaDocs
 */
public class Main extends Application {
    /**
     * Creates Initial instance of the main form
     * @param stage opens a window to view the main form
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 350);
        stage.setTitle("Inventory App");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the application
     * @param args
     */
    public static void main(String[] args) {

        launch(args);
    }
}