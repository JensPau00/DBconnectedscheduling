package Controller;

import ObjectsFromERD.Appointment;
import ObjectsFromERD.Contact;
import ObjectsFromERD.Customer;
import ObjectsFromERD.User;
import helper.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.Navigate;
import sample.timeValidation;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class addAppt {

    @FXML
    private Button addBTN;

    @FXML
    private TextField appointmentID;

    @FXML
    private ComboBox<Contact> contactCombo;

    @FXML
    private ComboBox<Customer> customerCombo;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private Button deleteBTN;

    @FXML
    private TextField desTF;

    @FXML
    private TextField endTF;

    @FXML
    private TextField locTF;

    @FXML
    private TextField startTF;

    @FXML
    private TextField titleTF;

    @FXML
    private TextField typeTF;

    @FXML
    private ComboBox<User> userCombo;
    private ContactDAOImp conDAO = new ContactDAOImp();
    private UsersDAOImp userDAO = new UsersDAOImp();
    private CustomerDAOImp cusDAO = new CustomerDAOImp();
    private AppointmentsDAOImp aptDAO = new AppointmentsDAOImp();

    /**
     * This method is called when the screen is initialized. It supplies the screen with all of the data it needs to perform it's functions
     * <p>
     *    This method takes no user input, but gets information about the user that is logged in so you can see who updated the appointment.
     * </p>**/
    @FXML
    public void initialize() throws SQLException {
        appointmentID.setDisable(true);
        appointmentID.setText("AUTOGEN");
        userCombo.setItems(userDAO.getAll());
        userCombo.getSelectionModel().select(Login.user);

        customerCombo.setItems(cusDAO.getAll());
        contactCombo.setItems(conDAO.getAll());
    }
    /**
     * This method uses the event caused by pressing the button. It also sends this event to the add method of navigate.
     * <p>
     *    This method saves the users data and stores it in the SQL data base for later use by the program and any users of the database.
     * </p>**/
    @FXML
    void onActionAdd(ActionEvent event) throws SQLException, IOException {
        try {
            List<Integer> start = new ArrayList<>();
            List<Integer> end = new ArrayList<>();
            start = timeValidation.getTime(startTF.getText());
            end = timeValidation.getTime(endTF.getText());
            if (timeValidation.startSooner(start, end)) {
                LocalDateTime timeStart = LocalDateTime.of(dateDatePicker.getValue().getYear(), dateDatePicker.getValue().getMonthValue(), dateDatePicker.getValue().getDayOfMonth(), start.get(0), start.get(1));
                LocalDateTime timeEnd = LocalDateTime.of(dateDatePicker.getValue().getYear(), dateDatePicker.getValue().getMonthValue(), dateDatePicker.getValue().getDayOfMonth(), end.get(0), end.get(1));
                Timestamp tsStart = Timestamp.valueOf(timeStart);
                Timestamp tsEnd = Timestamp.valueOf(timeEnd);
                Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());
                Appointment appointment = new Appointment(titleTF.getText(), desTF.getText(), locTF.getText(), typeTF.getText(), tsStart, tsEnd, currentTime, Login.user.getUser_name(), currentTime, Login.user.getUser_name(), customerCombo.getValue().getCustomer_ID(), userCombo.getValue().getUser_id(), contactCombo.getValue().getContact_id());
                if (cusValidation.validate(cusDAO.get(customerCombo.getValue().getCustomer_ID()),tsStart.toLocalDateTime(),tsEnd.toLocalDateTime(),appointment)){
                    AppointmentsDAOImp apptDAO = new AppointmentsDAOImp();
                    apptDAO.insert(appointment);
                    Navigate.add(event);
                }
            }
        }
        catch(Exception exception){
            System.out.println(exception);
        }
    }
    /**
     * This method uses the event caused by pressing the button. It also sends this event to the cancel method of navigate.
     * <p>
     *    This method goes back to the main menu screen using the cancel method of navigate.
     * </p>**/
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        Navigate.cancel("../View/Main menu.fxml",actionEvent);
    }
}
