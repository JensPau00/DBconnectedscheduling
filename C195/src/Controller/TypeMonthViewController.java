package Controller;

import ObjectsFromERD.Appointment;
import helper.AppointmentsDAOImp;
import helper.TypeMonthHelper;
import helper.Types;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;
import sample.Navigate;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TypeMonthViewController {

    @FXML
    private TableView<Types> TV;

    @FXML
    private Button exit;

    @FXML
    private ComboBox<String> monthCombo;

    @FXML
    private TableColumn number;

    @FXML
    private TableColumn type;
    TypeMonthHelper tmh = new TypeMonthHelper();


    @FXML
    public void initialize(){
        TypeMonthHelper.setMonths();
        monthCombo.setItems(TypeMonthHelper.getMonths());
    }
    @FXML
    void onActionExit(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load((Main.class.getResource("../View/Main menu.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSort(ActionEvent event) throws SQLException {
        String month = monthCombo.getValue();
        month = month.toUpperCase();
        tmh.setType(month);
        TV.refresh();
        TV.setItems(tmh.getTypes());
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        number.setCellValueFactory(new PropertyValueFactory<>("number"));

        System.out.println(LocalDateTime.now().getMonth());
    }

}
