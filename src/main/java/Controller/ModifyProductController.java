package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyProductController {
    public void returnToMainScreen(ActionEvent event) throws IOException {
        System.out.println("Clicked");
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onCancelButtonClicked(ActionEvent event) throws IOException {
        returnToMainScreen(event);

    }

    @FXML
    public void onSaveButtonClicked(ActionEvent event) throws IOException{
        returnToMainScreen(event);

    }
}
