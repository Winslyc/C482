package Controller;

import Main.Main;
import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {
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
    private Part selectedPart;

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
    @FXML
    public void saveButtonEvent(ActionEvent event) throws  IOException{
        try {
            int id = 0;
            String name = nameTextField.getText();
            Double price = Double.parseDouble(priceTextField.getText());
            int stock = Integer.parseInt(inventoryTextField.getText());
            int min = Integer.parseInt(minTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            int machineId;
            String companyName;
            boolean saveSuccessful = false;


            if(name.isEmpty()){
                displayAlert(5);
            } else {
                if(checkMin(min, max) && checkInventory(min, max, stock)){
                    if(inHouseRadioButton.isSelected()){
                        try{
                            machineId = Integer.parseInt(machineIdTextField.getText());
                            selectedPart = new InHouse(selectedPart.getId(), name, price, stock, min, max, machineId);

                            saveSuccessful = true;
                        }
                        catch (Exception e){
                            displayAlert(2);
                        }
                    }
                    if(outSourcedRadioButton.isSelected()){

                        companyName = machineIdTextField.getText();
                        Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
                        newOutsourcedPart.setId(Inventory.getNewPartID());
                        Inventory.addPart(newOutsourcedPart);
                        saveSuccessful = true;
                    }
                    if(saveSuccessful = true){
                        returnToMainScreen(event);
                    }
                }
            }


        } catch (Exception e){
            displayAlert(1);
        }
    }
    private boolean checkInventory(int min, int max, int stock) {
        boolean isValid = true;
        if(stock > max  || stock < min ) {
            isValid = false;
            displayAlert(4);
        }
        return isValid;
    }
    private boolean checkMin(int min, int max) {
        boolean isValid = true;
        if(min <= 0 || min >= max){
            isValid = false;
            displayAlert(3);
        }

        return isValid;
    }
    private void displayAlert(int displayAlert) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch(displayAlert) {

            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Part");
                alert.setContentText("Form contains blank fields or invalid values.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Machine ID");
                alert.setContentText("Machine ID may only contain numbers.");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Min");
                alert.setContentText("Min must be a number greater than 0 and less than Max.");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Inventory");
                alert.setContentText("Inventory must be a number equal to or between Min and Max.");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("Name Empty");
                alert.setContentText("Name cannot be empty.");
                alert.showAndWait();
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inHouseRadioButton.setSelected(true);
        selectedPart = MainController.getPartToModify();
        idTextField .setText(String.valueOf(selectedPart.getId()));
        nameTextField.setText(selectedPart.getName());
        priceTextField.setText(String.valueOf(selectedPart.getPrice()));
        inventoryTextField.setText(String.valueOf(selectedPart.getStock()));
        minTextField.setText(String.valueOf(selectedPart.getMin()));
        maxTextField.setText(String.valueOf(selectedPart.getMax()));
    }


}
