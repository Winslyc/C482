package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyPartController {
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
        Alert cancelAlert =new Alert(Alert.AlertType.WARNING);
        cancelAlert.setContentText("You are being redirected back to the main application");
        cancelAlert.showAndWait();
        returnToMainScreen(event);


    }

    @FXML
    public void onSaveButtonClicked(ActionEvent event) throws IOException{
        returnToMainScreen(event);

    }
}
