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
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {
    /**
     * Checks if part is Inhouse;
     */
    private boolean isPartInHouse;
    /**
     * The inhouse radio button
     */
    @FXML
    private RadioButton inHouseRadioButton;
    /**
     * The toggle group for Radio buttons
     */
    @FXML
    private ToggleGroup tgPartType;
    /**
     * The outsourced radio button
     */

    @FXML
    private RadioButton outSourcedRadioButton;
    /**
     * The name textField
     */
    @FXML
    private TextField idTextField;
    /**
     * The Name Text Field
     */
    @FXML
    private TextField nameTextField;
    /**
     * The inventory text field
     */
    @FXML
    private TextField inventoryTextField;
    /**
     * The price text field
     */
    @FXML
    private TextField priceTextField;
    /**
     *the part maximum text field
     */
    @FXML
    private TextField maxTextField;
    /**
     * The minimum text field
     */
    @FXML
    private TextField minTextField;
    /**
     * The machineID/Company Name text field
     */
    @FXML
    private TextField machineIdTextField;

    /**
     * The machine id Text
     */
    @FXML
    private Text machineID;
    /**
     * The part selected to Modify
     */
    private Part selectedPart;
    /**
     * Returns user to the main screen
     *     ** @param event refers to the action event where a button is clicked
     *      * @throws IOException from FXML Loader
     */
    public void returnToMainScreen(ActionEvent event) throws IOException {
        System.out.println("Clicked");
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Cancels a part and returns user to the main screen
     *
     ** @param event refers to the action event where a button is clicked
     * @throws IOException from FXML Loader
     * */
    @FXML
    public void onCancelButtonClicked(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want cancel changes and return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMainScreen(event);
        }

    }

    /**
     *Switches the radio button that is selected.
     *
     */
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
    /**
     * Saves a new Part and adds it to Inventory
     *
     ** @param event refers to the action event where a button is clicked
     * @throws IOException from FXML Loader
     */
    @FXML
    public void saveButtonEvent(ActionEvent event) throws  IOException{
        try {
            int id = selectedPart.getId();
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
                            Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));

                            saveSuccessful = true;
                        }
                        catch (Exception e){
                            displayAlert(2);
                        }
                    }
                    if(outSourcedRadioButton.isSelected()){

                        companyName = machineIdTextField.getText();
                        Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
                        Inventory.addPart(newOutsourcedPart);
                        saveSuccessful = true;
                    }
                    if(saveSuccessful = true){
                        Inventory.deletePart(selectedPart);
                        returnToMainScreen(event);
                    }
                }
            }


        } catch (Exception e){
            displayAlert(1);
        }
    }
    /**
     *Checks if inputted inventory value is valid
     *
     * @param min the minimum inventory value
     * @param max the maximum inventory value
     * @param stock the current inventory stock
     */
    private boolean checkInventory(int min, int max, int stock) {
        boolean isValid = true;
        if(stock > max  || stock < min ) {
            isValid = false;
            displayAlert(4);
        }
        return isValid;
    }
     /**
      * Validates the part minimum being less than the max
     * @param min the minimum inventory amount
     * @param max the maximum inventory amount
     */
    private boolean checkMin(int min, int max) {
        boolean isValid = true;
        if(min <= 0 || min >= max){
            isValid = false;
            displayAlert(3);
        }

        return isValid;
    }
    /**
     * Displays an alert when an error occurs in use
     *
     * @param displayAlert  selects Error message to display
     */
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

    /**
     * Initializes the Controller
     *
     * @param url the location used to resolve relative paths
     * @param resourceBundle the resources used to localize the object root
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedPart = MainController.getPartToModify();
        if(selectedPart instanceof InHouse){
            inHouseRadioButton.setSelected(true);
            machineID.setText("Machine ID");
            machineIdTextField.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        }

        if(selectedPart instanceof Outsourced){
            outSourcedRadioButton.setSelected(true);
            machineID.setText("Company Name");
            machineIdTextField.setText(((Outsourced) selectedPart).getCompanyName());
        }


        idTextField .setText(String.valueOf(selectedPart.getId()));
        nameTextField.setText(selectedPart.getName());
        priceTextField.setText(String.valueOf(selectedPart.getPrice()));
        inventoryTextField.setText(String.valueOf(selectedPart.getStock()));
        minTextField.setText(String.valueOf(selectedPart.getMin()));
        maxTextField.setText(String.valueOf(selectedPart.getMax()));
    }

}
