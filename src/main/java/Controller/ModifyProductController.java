package Controller;

import Main.Main;
import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**     FUTURE ENHANCEMENT
 * Make it so that the parts list updates as keys are pressed instead of when enter is pressed.
 *
 */
public class ModifyProductController implements Initializable {
    /**
     * The product to modify
     */
    Product productToMOdify = MainController.getProductToModify();
    /**
     * The list of associated parts of a product
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    /**
     * The Name text Field
     */
    @FXML private TextField productNameTextField;
    /**
     * The Inventory TextField
     */
    @FXML private TextField productStockTextField;
    /**
     * The price text field
     */
    @FXML private TextField productPriceTextField;
    /**
     * the  maximum text field
     */
    @FXML private TextField productMaxTextField;
    /**
     * the minimum text field
     */
    @FXML private TextField productMinTextField;
    /**
     * the part search text field
     */
    @FXML private TextField partSearchTextField;
    /**
     * The id text field
     */
    @FXML private TextField idTextField;
    /**
     * The parts table view
     */
    @FXML private TableView<Part> allPartsTableView;
    /**
     * The table id column
     */
    @FXML private TableColumn<Part, Integer> allPartsIDColumn;
    /**
     * the parts table name column
     */
    @FXML private TableColumn<Part, String> allPartsNameColumn;

    /**
     * the part inventory column
     */
    @FXML private TableColumn<Part, Integer> allPartsStockColumn;
    /**
     * the parts table price column
     */
    @FXML private TableColumn<Part, Double> allPartsPriceColumn;
    /**
     *The associated parts table view
     */
    @FXML private TableView<Part> associatedPartsTableView;
    /**
     *The associated parts table id column
     */
    @FXML private TableColumn<Part, Integer> associatedPartsIDColumn;
    /**
     *The associated parts name column
     */
    @FXML private TableColumn<Part, String> associatedPartsNameColumn;
    /**
     *The associated parts inventory column
     */
    @FXML private TableColumn<Part, Integer> associatedPartsStockColumn;
    /**
     *The associated parts price column
     */
    @FXML private TableColumn<Part, Double> associatedPartsPriceColumn;
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
     * Searches for parts in list of all parts and updates the table view
     *
     ** @param event refers to the action event where a button is clicked
     * @throws IOException from FXML Loader
     * */

    @FXML private void partSearchBtnAction(ActionEvent event){
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> foundParts = FXCollections.observableArrayList();

        String searchText = partSearchTextField.getText();

        for(Part part : allParts){
            if(String.valueOf(part.getId()).contains(searchText) || part.getName().contains(searchText)){
                foundParts.add(part);
            }
        }
        allPartsTableView.setItems(foundParts);
        if(foundParts.isEmpty()){
            displayAlert(1);
        }
    }
    /**
     * Refreshes the table view to show all parts when the search bar is empty
     *
     ** @param event refers to the Keyevent event where a key is presssed
     * */
    /**
     * RUNTIME ERROR
     * key events will cause an error when used to set an action event in fxml.
     * Created partSearchButton action to resolve error.
     * @param event
     */
    @FXML private void partSearchTextKeyPressed(KeyEvent event){
        if(partSearchTextField.getText().isEmpty()){
            allPartsTableView.setItems(Inventory.getAllParts());
        }
    }
    /**
     * Adds a part to the associated parts list and updates the table view
     *
     ** @param event refers to the action event where a button is clicked
     */
    @FXML public  void addActionButton(ActionEvent event){
        Part selectedPart = allPartsTableView.getSelectionModel().getSelectedItem();
        if(selectedPart ==null){
            displayAlert(5);
        } else{
            associatedParts.add(selectedPart);
            associatedPartsTableView.setItems(associatedParts);
        }
    }
    /**
     * Removes an associated part from the associate parts list
     *
     * @param event refers to the action event where a button is clicked
     */
    @FXML public void removeAssocPart(ActionEvent event){
        Part selectedPart = associatedPartsTableView.getSelectionModel().getSelectedItem();
        if(selectedPart != null) {
            associatedParts.remove(selectedPart);
            associatedPartsTableView.setItems(associatedParts);
        } else {
            displayAlert(6);
        }
    }
    /**
     * Saves the new product and adds it to the all products list. Then returns the user to the main page
     *
     ** @param event refers to the action event where a button is clicked
     * @throws IOException from FXML Loader
     * */
    @FXML
    public void onSaveButtonClicked(ActionEvent event) throws IOException {
        try {
            int id = productToMOdify.getId();
            String name = productNameTextField.getText();
            Double price = Double.parseDouble(productPriceTextField.getText());
            int stock = Integer.parseInt(productStockTextField.getText());
            int min = Integer.parseInt(productMinTextField.getText());
            int max = Integer.parseInt(productMaxTextField.getText());


            if (name.isEmpty()) {
                displayAlert(5);
            } else {
                if (checkMin(min, max) && checkInventory(min, max, stock)) {

                    Product newProduct = new Product(id, name, price, stock, min, max);

                    for (Part part : associatedParts) {
                        newProduct.addAssociatedPart(part);
                    }


                    Inventory.addProduct(newProduct);
                    Inventory.deleteProduct(productToMOdify);
                    returnToMainScreen(event);
                    System.out.print(Inventory.getAllProducts().get(0).getName());
                }
            }
        } catch (Exception e) {
            displayAlert(1);
        }
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
                alert.setHeaderText("Error Adding Product");
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
            case 6:
                alert.setTitle("Error");
                alert.setHeaderText("No part Selected");
                alert.setContentText("A part must be selected to remove from the product.");
                alert.showAndWait();

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
     *Validates the part minimum being less than the max
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
     * Initializes the Controller
     *
     * @param url the location used to resolve relative paths
     * @param resourceBundle the resources used to localize the object root
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        associatedParts = productToMOdify.getAllAssociatedParts();
        //All parts table view
        allPartsTableView.setItems(Inventory.getAllParts());
        allPartsIDColumn.setCellValueFactory( new PropertyValueFactory<>("id"));
        allPartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartsStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartsTableView.setItems(associatedParts);
        associatedPartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartsNameColumn.setCellValueFactory( new PropertyValueFactory<>("name"));
        associatedPartsStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productNameTextField.setText(productToMOdify.getName());
        productMaxTextField.setText(String.valueOf(productToMOdify.getMax()));
        productStockTextField.setText(String.valueOf(productToMOdify.getStock()));
        productMinTextField.setText(String.valueOf(productToMOdify.getMin()));
        productPriceTextField.setText(String.valueOf(productToMOdify.getPrice()));
        idTextField.setText(String.valueOf(productToMOdify.getId()));

    }


}
