package Controller;

import ObjectsFromERD.Appointment;
import ObjectsFromERD.Contact;
import ObjectsFromERD.Customer;
import ObjectsFromERD.User;
import helper.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Navigate;
import sample.timeValidation;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class updateAppt {

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
    private Appointment appt;

    @FXML
    private ComboBox<User> userCombo;
    private ContactDAOImp conDAO = new ContactDAOImp();
    private UsersDAOImp userDAO = new UsersDAOImp();
    private CustomerDAOImp cusDAO = new CustomerDAOImp();
    @FXML
    public void sendAppt(Appointment appointment) throws SQLException {
        this.appt = appointment;
        appointmentID.setText(Integer.toString(appointment.getAppointment_id()));
        titleTF.setText(appointment.getTitle());
        desTF.setText(appointment.getDescription());
        locTF.setText(appointment.getLocation());
        typeTF.setText(appointment.getType());
        Timestamp time = appointment.getStart();
        LocalDateTime date = time.toLocalDateTime();
        LocalDate date1 = date.toLocalDate();
        dateDatePicker.setValue(date1);
        String timeStringStart="";
        String timeStringEnd="";
        if (date.getHour()<10){
            timeStringStart += "0"+date.getHour();
        }
        else{
            timeStringStart+=date.getHour();
        }
        timeStringStart += ":";
        if (date.getMinute()<10){
            timeStringStart +="0"+date.getMinute();
        }
        else{
            timeStringStart+=date.getMinute();
        }
        startTF.setText(timeStringStart);
        time = appointment.getEnd();
        date = time.toLocalDateTime();
        if (date.getHour()<10){
            timeStringEnd += "0"+date.getHour();
        }
        else{
            timeStringEnd+=date.getHour();
        }
        timeStringEnd += ":";
        if (date.getMinute()<10){
            timeStringEnd += "0"+date.getMinute();
        }
        else{
            timeStringEnd+=date.getMinute();
        }
        endTF.setText(timeStringEnd);
        userCombo.setItems(userDAO.getAll());
        User selectedUser = userDAO.get(appt.getUser_id());
        userCombo.getSelectionModel().select(selectedUser);

        customerCombo.setItems(cusDAO.getAll());
        Customer selectedCus = cusDAO.get(appt.getCustomer_id());
        customerCombo.getSelectionModel().select(selectedCus);

        contactCombo.setItems(conDAO.getAll());
        Contact selectedCon = conDAO.get(appt.getContact_id());
        contactCombo.getSelectionModel().select(selectedCon);

    }

    @FXML
    public void initialize() throws SQLException {
        appointmentID.setDisable(true);
        userCombo.setItems(userDAO.getAll());
        customerCombo.setItems(cusDAO.getAll());
        contactCombo.setItems(conDAO.getAll());
    }

    @FXML
    void onActionUpdate(ActionEvent event) throws SQLException, IOException {
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

                if (cusValidation.validate(cusDAO.get(this.appt.getCustomer_id()),tsStart.toLocalDateTime(),tsEnd.toLocalDateTime(),this.appt)){
                    this.appt.setTitle(titleTF.getText());
                    this.appt.setDescription(desTF.getText());
                    this.appt.setLocation(locTF.getText());
                    this.appt.setType(typeTF.getText());
                    this.appt.setStart(tsStart);
                    this.appt.setEnd(tsEnd);
                    this.appt.setLast_update(currentTime);
                    this.appt.setLast_updated_by(Login.user.getUser_name());
                    this.appt.setCustomer_id(customerCombo.getValue().getCustomer_ID());
                    this.appt.setUser_id(userCombo.getValue().getUser_id());
                    this.appt.setContact_id(contactCombo.getValue().getContact_id());AppointmentsDAOImp apptDAO = new AppointmentsDAOImp();
                apptDAO.update(this.appt);
                Navigate.add(event);
            }
            }
        }
        catch (Exception e){

        }
    }
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        Navigate.cancel("../View/Main menu.fxml",actionEvent);
    }
}
