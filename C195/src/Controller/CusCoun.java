package Controller;

import ObjectsFromERD.Country;
import helper.CountryDAOImp;
import helper.CustomerCountry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.sql.SQLException;

public class CusCoun {

    @FXML
    private TableColumn<?, ?> Country;

    @FXML
    private TableView<CustomerCountry> TV;

    @FXML
    private Button exit;

    @FXML
    private TableColumn<?, ?> numberOfCus;
    @FXML
    void initialize() throws SQLException {
        CountryDAOImp country = new CountryDAOImp();
        CustomerCountry holder = null;
        ObservableList<CustomerCountry> countries = FXCollections.observableArrayList();
        for (ObjectsFromERD.Country country1:country.getAll()){
            holder = new CustomerCountry(country1);
            countries.add(holder);
            holder.countCus();
        }
        TV.setItems(countries);
        Country.setCellValueFactory(new PropertyValueFactory<>("country"));
        numberOfCus.setCellValueFactory(new PropertyValueFactory<>("cusCount"));
    }
    @FXML
    void onActionExit(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load((Main.class.getResource("../View/Main menu.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();}
    }

