package Controller;

import ObjectsFromERD.Appointment;
import ObjectsFromERD.Contact;
import helper.AppointmentsDAOImp;
import helper.ContactDAOImp;
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

import javax.management.openmbean.CompositeData;
import java.io.IOException;
import java.sql.SQLException;

public class ConApt {

    @FXML
    private ComboBox<Contact> ContactSelect;

    @FXML
    private TableColumn<?, ?> apptDes;

    @FXML
    private TableColumn<?, ?> apptEnd;

    @FXML
    private TableColumn<?, ?> apptId;

    @FXML
    private TableColumn<?, ?> apptLoc;

    @FXML
    private TableColumn<?, ?> apptStart;

    @FXML
    private TableColumn<?, ?> apptTitle;

    @FXML
    private TableColumn<?, ?> apptType;

    @FXML
    private TableColumn<?, ?> aptCusId;

    @FXML
    private TableView<Appointment> aptTV;

    @FXML
    private Button exit;
    private AppointmentsDAOImp apt = new AppointmentsDAOImp();
@FXML
void initialize() throws SQLException {
    ContactDAOImp conDAO=new ContactDAOImp();
    ContactSelect.setItems(conDAO.getAll());
    apptStart.setCellValueFactory(new PropertyValueFactory<>("start"));
    apptEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
    apptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
    apptId.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
    apptDes.setCellValueFactory(new PropertyValueFactory<>("description"));
    apptLoc.setCellValueFactory(new PropertyValueFactory<>("location"));
    apptType.setCellValueFactory(new PropertyValueFactory<>("type"));
    aptCusId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));

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
        aptTV.setItems(apt.getAllByContactID(ContactSelect.getSelectionModel().getSelectedItem().getContact_id()));
    }

}
