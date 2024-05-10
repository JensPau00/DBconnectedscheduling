package sample;
import Controller.Login;
import Controller.updateCusController;
import ObjectsFromERD.Customer;
import helper.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String s= String.valueOf(ZoneId.systemDefault());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../View/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setTitle(s);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException, IOException {
        jdbc.openConnection();
        launch();
        Login.out.close();
        jdbc.closeConnection();
    }
}