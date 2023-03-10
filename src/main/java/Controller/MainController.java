package Controller;
import Main.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import  javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {




    @FXML
    protected void onAddPartClicked(ActionEvent event) throws IOException {
    Parent parent =FXMLLoader.load(getClass().getResource("/View/AddPart.fxml"));
    Scene scene = new Scene(parent);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    @FXML
    protected void onModifyPartClicked(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("/View/ModifyPart.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void onModifyProductClicked(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("/View/ModifyProduct.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void onAddProductClicked(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("/View/AddProduct.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void onExitClicked(ActionEvent event){
        Platform.exit();
    }

 }