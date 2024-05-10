package sample;

import ObjectsFromERD.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.util.EventObject;



public abstract class Navigate {
        public static Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        public static void cancel(String page, ActionEvent actionEvent) throws IOException {
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("");
        alert.setContentText("Are you sure you'd like to quit? Progress will not be saved.");
        alert.showAndWait();
        if (alert.getResult()== ButtonType.OK) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load((Main.class.getResource(page)));
        stage.setScene(new Scene(scene));
        stage.show();}
    }
    public static void add(ActionEvent actionEvent) throws IOException {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load((Main.class.getResource("../View/Main menu.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
}
