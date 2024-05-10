package Controller;

import ObjectsFromERD.Appointment;
import ObjectsFromERD.Customer;
import helper.*;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.AlertError;
import sample.Main;
import sample.nextScreen;
import sample.timeValidation;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class mainMenuController {
    @FXML
    private ToggleGroup AppointmentSort;

    @FXML
    private Button addCustomer;

    @FXML
    private TableColumn apptCon;

    @FXML
    private DatePicker apptDate;

    @FXML
    private TableColumn apptDes;

    @FXML
    private TableColumn apptEnd;

    @FXML
    private TableColumn apptId;

    @FXML
    private TableColumn apptStart;

    @FXML
    private TextField apptTimeStartTF;
    @FXML
    private TextField apptTimeEndTF;

    @FXML
    private TableColumn apptTitle;

    @FXML
    private TableColumn apptType;

    @FXML
    private Button apptrep;

    @FXML
    private TableColumn<?, ?> aptCusId;

    @FXML
    private TableView<Appointment> aptTV;

    @FXML
    private TableColumn aptUserID;

    @FXML
    private Button cs;

    @FXML
    private TableColumn cusAdd;

    @FXML
    private Button cusByCounBTN;

    @FXML
    private TableColumn<Customer, String> cusFirst;

    @FXML
    private TableColumn cusID;

    @FXML
    private TableColumn cusName;

    @FXML
    private TableColumn cusPos;
    // This lambda is used to go to the next screen without needing redundant code.
    private nextScreen addLambda = (event,screen,loader)->{
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load((Main.class.getResource(screen)));
        stage.setScene(new Scene(scene));
        stage.show();
    };
    //this lambda is used in update calls to reduce redundant code.
    private nextScreen updateLambda = (event,screen,loader)->{
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    };

    @FXML
    private TableView<Customer> cusTV;

    @FXML
    private TableColumn cusPho;
    @FXML
    private RadioButton weekBTN;
    @FXML
    private RadioButton monthBTN;
    @FXML
    private RadioButton allAPTBTN;
    private static boolean loginFlag = false;


    @FXML
    private Button deleteAptBTN;

    @FXML
    private Button deleteCusbtn;

    @FXML
    private Button updateCustomer;
    @FXML
    private TableColumn apptLoc;
    mainScreenTableViewStorage ms = new mainScreenTableViewStorage();
    @FXML
    private Button updateTimeBTN;
    CustomerDAOImp cusDAO = new CustomerDAOImp();
    AppointmentsDAOImp aptDAO = new AppointmentsDAOImp();
    FirstDivDAOImp firstDAO = new FirstDivDAOImp();
    /**
     * This lambda is used to show an alert when attempts to delete a customer and appointment are made.
     * <p>
     *    This method takes no inputs.
     * </p>**/
    AlertError lambdaAlert = (header, content, error) -> {
        AlertError.alert.setHeaderText(header);
        AlertError.alert.setContentText(content);
        AlertError.alert.setTitle(error);
        AlertError.alert.showAndWait();};

    public mainMenuController() throws SQLException {
    }
    /**
     * When the scene is created all of the tables need to be initialized. If the user just logs in they will get an alert for appointments that are within 15 minutes.
     * <p>
     *    This method takes no inputs.
     * </p>**/

    @FXML
    public void initialize() throws SQLException {

        cusTV.setItems(ms.getCustomers());
        cusID.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
        cusName.setCellValueFactory(new PropertyValueFactory<>("customer_name"));

        cusFirst.setCellValueFactory(new PropertyValueFactory<>("division_name"));

        cusPho.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cusPos.setCellValueFactory(new PropertyValueFactory<>("postal_code"));
        cusAdd.setCellValueFactory(new PropertyValueFactory<>("address"));
        aptTV.setItems(ms.getAppointments());
        apptCon.setCellValueFactory(new PropertyValueFactory<>("contact_id"));
        apptStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptId.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        apptDes.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptLoc.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptType.setCellValueFactory(new PropertyValueFactory<>("type"));
        aptCusId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        aptUserID.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        boolean isSoon = false;
        if (!loginFlag){
        for (Appointment appointment : ms.getAppointments()){
            boolean isUser = appointment.getUser_id()==Login.user.getUser_id();
            if (appointment.getStart().toInstant().minusSeconds(900).isBefore(Instant.now())&&appointment.getStart().toInstant().isAfter(Timestamp.valueOf(LocalDateTime.now()).toInstant())&&isUser){
                isSoon = true;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Your appointment "+ String.valueOf(appointment.getAppointment_id())+ " is at "+ appointment.getStart());
                alert.show();
            }
        }
        if (!isSoon){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("No Appointments");
            alert.show();
        }
        loginFlag=true;
        }

    }
    /**
     * When the button is pressed it goes to the addAppt.FXML file.
     * <p>
     *    This takes the button event and uses addLamdba to go to the correct screen.
     * </p>**/
    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException {
        addLambda.next(event,"../View/addAppt.fxml",null);
    }
    /**
     * When the button is pressed it goes to the addCus.FXML file.
     * <p>
     *    This takes the button event and uses addLamdba to go to the correct screen.
     * </p>**/
    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException {
        addLambda.next(event,"../View/addCus.fxml",null);
    }
    /**
     * When the button is pressed it sorts the appointments into all appointments.
     * <p>
     *    This takes the button event and gets appointments which are stored in ms(MainscreenTableViewStorage).
     * </p>**/
    @FXML
    void onActionAllAppointments(ActionEvent event) {
        aptTV.setItems(ms.getAppointments());
        aptTV.refresh();
    }
    /**
     * Deletes selected item from the Appointment Database.
     * <p>
     *    This method takes event from the button press. It checks the main screen controllers to ensure that the items are removed from all sorts.
     * </p>**/
    @FXML
    void onActionDeleteApt(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Delete Appointment?\nID: "+aptTV.getSelectionModel().getSelectedItem().getAppointment_id()+"\n"+"Type: "+aptTV.getSelectionModel().getSelectedItem().getType());
        alert.showAndWait();
        if (alert.getResult()==ButtonType.OK) {
            ms.deleteAppt(aptTV.getSelectionModel().getSelectedItem());
            if (weekBTN.isSelected()) {
                aptTV.setItems(mainScreenTableViewStorage.weeklySort());
                aptTV.refresh();
            }
            if (monthBTN.isSelected()) {
                aptTV.setItems(mainScreenTableViewStorage.monthlySort());
                aptTV.refresh();
            }
        }
    }
    /**
     * Deletes selected item from the Customer Database. It also deletes all of that customers appointments
     * <p>
     *    This method takes event from the button press. It checks the main screen controllers to ensure that the items are removed from all sorts.
     * </p>**/
    @FXML
    void onActionDeleteCustomer(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Delete Customer\nName: "+cusTV.getSelectionModel().getSelectedItem().getCustomer_name());
        alert.showAndWait();
        if (alert.getResult()==ButtonType.OK) {
            ms.deleteCus(cusTV.getSelectionModel().getSelectedItem());
            aptTV.refresh();
        }
    }

    @FXML
    void onActionMonth(ActionEvent event) {
        aptTV.setItems(mainScreenTableViewStorage.monthlySort());
        aptTV.refresh();
    }

    @FXML
    void onActionShowAppts(ActionEvent event) throws IOException {
        addLambda.next(event,"../View/TypeMonthView.fxml",null);
    }

    @FXML
    void onActionShowContactShedule(ActionEvent event) throws IOException {
        addLambda.next(event,"../View/contactAptSchedule.fxml",null);
    }

    @FXML
    void onActionShowCustomersByCountry(ActionEvent event) throws IOException {
        addLambda.next(event,"../View/cuscounRep.fxml",null);
    }

    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException, SQLException {
        Customer customer = cusTV.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((Main.class.getResource("../View/updateCus.fxml")));
        loader.load();
        updateCusController UCController = loader.getController();
        UCController.sendCustomer(customer);
        updateLambda.next(event,null,loader);
    }

    @FXML
    void onActionUpdateSelectedTime(ActionEvent event) throws SQLException {
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        start = timeValidation.getTime(apptTimeStartTF.getText());
        end = timeValidation.getTime(apptTimeEndTF.getText());
        Appointment appt = aptTV.getSelectionModel().getSelectedItem();
        LocalDateTime timeStart = LocalDateTime.of(apptDate.getValue().getYear(), apptDate.getValue().getMonthValue(), apptDate.getValue().getDayOfMonth(), start.get(0), start.get(1));
        LocalDateTime timeEnd = LocalDateTime.of(apptDate.getValue().getYear(), apptDate.getValue().getMonthValue(), apptDate.getValue().getDayOfMonth(), end.get(0), end.get(1));

        try {
            if (timeValidation.startSooner(start, end)) {
                Timestamp tsStart = Timestamp.valueOf(timeStart);
                Timestamp tsEnd = Timestamp.valueOf(timeEnd);
                Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());

                if(cusValidation.validate(cusDAO.get(appt.getCustomer_id()), timeStart, timeEnd, appt)){
                    appt.setStart(tsStart);
                    appt.setEnd(tsEnd);
                    appt.setLast_update(currentTime);
                    AppointmentsDAOImp apptDAO = new AppointmentsDAOImp();
                    apptDAO.update(appt);
                }
                aptTV.refresh();
            }
            if (weekBTN.isSelected()) {
                aptTV.setItems(mainScreenTableViewStorage.weeklySort());
                aptTV.refresh();
            }
            if (monthBTN.isSelected()) {
                aptTV.setItems(mainScreenTableViewStorage.monthlySort());
                aptTV.refresh();
            }
        }
        catch (Exception e){

        }
    }

    @FXML
    void onActionWeek(ActionEvent event) {
        aptTV.setItems(mainScreenTableViewStorage.weeklySort());
        aptTV.refresh();
    }

    @FXML
    void onActonUpdateApt(ActionEvent event) throws IOException, SQLException {
        Appointment appointment = aptTV.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((Main.class.getResource("../View/updateAppt.fxml")));
        loader.load();
        updateAppt UCController = loader.getController();
        UCController.sendAppt(appointment);
        updateLambda.next(event,null,loader);
    }

}
