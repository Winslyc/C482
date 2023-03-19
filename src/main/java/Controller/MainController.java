package Controller;
import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import  javafx.fxml.FXML;
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

public class MainController implements Initializable {
    private static Part partToModify;
    private static Product productToModify;
    //Parts Table
    /**
     * The Parts Table View
     */
    @FXML private TableView <Part> partTableView;
    /**
     * The ID table Column
     */

    @FXML private TableColumn<Part, Integer> partID;
    /**
     * The Name table Column
     */
    @FXML private TableColumn<Part, String> partName;
    /**
     * The Parts inventory column
     */
    @FXML private TableColumn<Part, Integer> inventoryLevel;
    /**
     * The parts Price Column
     */
    @FXML private TableColumn<Part, Double> priceColumn;
    /**
     * The Parts search TextField
     */
    @FXML private TextField partSearchTextField;
    //Product Table
    /**
     * The Product Table View
     */
    @FXML private TableView<Product> productsTableView;
    /**
     * The Product table ID Column
     */
    @FXML private TableColumn<Product, Integer> productIdColumn;
    /**
     * The Product Name Column
     */
    @FXML private TableColumn<Product, String > productNameColumn;
    /**
     * The Product Inventory Column
     */
    @FXML private TableColumn<Product, Integer> inventoryLevelColumn;
    /**
     * The Product Price Column
     */
    @FXML private TableColumn<Product, Double> productPriceColumn;
   @FXML private TextField productSearchTextField;
    @FXML private  void productsDeleteBtnAction(ActionEvent event){
         Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            displayAlert(2);
        } else {
        if (selectedProduct.hasAssociatedParts()) {
            displayAlert(5);
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected part?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deleteProduct(selectedProduct);
            }
        }
        }
    }

    @FXML private void partSearchBtnAction(ActionEvent event){
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        String searchText = partSearchTextField.getText();

        for(Part part : allParts){
           if(String.valueOf(part.getId()).contains(searchText) || part.getName().contains(searchText)){
           foundParts.add(part);
           }
        }
        partTableView.setItems(foundParts);
    if(foundParts.isEmpty()){
        displayAlert(1);
    }
    }
    @FXML private void productSearchTextKeyPressed(KeyEvent event){
    if(productSearchTextField.getText().isEmpty()){
        productsTableView.setItems(Inventory.getAllProducts());
    }
    }
    @FXML private void productSearchBtnAction(ActionEvent event){
        ObservableList<Product> allProducts= Inventory.getAllProducts();
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        String searchText = partSearchTextField.getText();

        for(Product product: allProducts){
            if(String.valueOf(product.getId()).contains(searchText) || product.getName().contains(searchText)){
                foundProducts.add(product);
            }
        }
        productsTableView.setItems(foundProducts);
        if(foundProducts.isEmpty()){
            displayAlert(2);
        }
    }
    @FXML private void partSearchTextKeyPressed(KeyEvent event){
        if(partSearchTextField.getText().isEmpty()){
            partTableView.setItems(Inventory.getAllParts());
        }
    }
    @FXML
    protected void onAddPartClicked(ActionEvent event) throws IOException {
    Parent parent =FXMLLoader.load(getClass().getResource("/View/AddPart.fxml"));
    Scene scene = new Scene(parent);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();

    }
    @FXML
    private void onDeletePartClicked() {
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(3);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
            Inventory.deletePart(partTableView.getItems().get(partTableView.getSelectionModel().getSelectedItem().getId() - 1));
            System.out.println(partTableView.getSelectionModel().getSelectedItem().getId());

        }
    }
    @FXML
    protected void onModifyPartClicked(ActionEvent event) throws IOException {
        partToModify = partTableView.getSelectionModel().getSelectedItem();
        if (partToModify == null) {
            displayAlert(3);
        } else {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/ModifyPart.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
        private void displayAlert(int alertType) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Alert alertError = new Alert(Alert.AlertType.ERROR);

            switch (alertType) {
                case 1:
                    alert.setTitle("Information");
                    alert.setHeaderText("Part not found");
                    alert.showAndWait();
                    break;
                case 2:
                case 6:
                    alert.setTitle("Information");
                    alert.setHeaderText("Product not found");
                    alert.showAndWait();
                    break;
                case 3:
                    alertError.setTitle("Error");
                    alertError.setHeaderText("Part not selected");
                    alertError.showAndWait();
                    break;
                case 4:
                    alertError.setTitle("Error");
                    alertError.setHeaderText("Product not selected");
                    alertError.showAndWait();
                    break;
                case 5:
                    alertError.setTitle("Error");
                    alertError.setHeaderText("Parts Associated");
                    alertError.setContentText("All parts must be removed from product before deletion.");
                    alertError.showAndWait();
                    break;
            }
        }



    @FXML
    protected void onModifyProductClicked(ActionEvent event) throws IOException{
        productToModify = productsTableView.getSelectionModel().getSelectedItem();
        if (productToModify == null) {
            displayAlert(3);
        } else {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/ModifyProduct.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

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

    public static Part getPartToModify() {
        return partToModify;
    }
    public static Product getProductToModify(){
       return productToModify;
    }

    /**
     * Initializes  Table View and allows parts to be added and seen with the observable list.
     *
     * @param location Location resolves relative paths and the root for an object.
     * @param resources The resources that localize a root object.
     */


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Fills Part TableView
        partTableView.setItems(Inventory.getAllParts());
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Fills Product Table View
        productsTableView.setItems(Inventory.getAllProducts());
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


}
