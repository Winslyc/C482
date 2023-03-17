package Controller;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPartController {
    private boolean isPartInHouse;
    @FXML
    private RadioButton inHouseRadioButton;
    @FXML
    private ToggleGroup tgPartType;
    @FXML
    private RadioButton outSourcedRadioButton;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField inventoryTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField maxTextField;
    @FXML
    private TextField minTextField;
    @FXML
    private TextField machineIdTextField;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Text machineID;


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
    @FXML
    public void radioButtonsToggled(){
        if(tgPartType.getSelectedToggle() == inHouseRadioButton) {
            System.out.println("Selected");
            machineID.setText("Machine ID");
            isPartInHouse = true;
        } else if(tgPartType.getSelectedToggle() == outSourcedRadioButton){
        machineID.setText("Company Name");
        System.out.println("Selected outsource");
        isPartInHouse = false;
        }

    }
    public void createPart(){

    }

    public void saveButtonEvent(ActionEvent event){

    }
}
