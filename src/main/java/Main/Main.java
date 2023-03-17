package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.*;

/**
 * Main Class that provides the launcher for the inventory application
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 350);
        stage.setTitle("Inventory App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}