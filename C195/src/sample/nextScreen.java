package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public interface nextScreen {
    Parent scene = null;
    Stage stage = null;
    void next(ActionEvent event, String screen,FXMLLoader loader) throws IOException;
}
