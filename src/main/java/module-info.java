module Model {
    requires javafx.controls;
    requires javafx.fxml;


    opens Model to javafx.fxml;
    exports Model;
    exports Main;
    exports Controller;
    opens Controller to javafx.fxml;
    opens Main to javafx.fxml;

}