package Controller;
import Main.Main;
import Model.Inventory;
import Model.Part;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import  javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
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
    }
}
