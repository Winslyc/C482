module Model {
    requires javafx.controls;
    requires javafx.fxml;


    opens Model to javafx.fxml;
    exports Model;

}