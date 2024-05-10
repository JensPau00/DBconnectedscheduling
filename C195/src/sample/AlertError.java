package sample;

import javafx.scene.control.Alert;

public interface AlertError {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    void displayAlert(String header,String content,String Title);
}
