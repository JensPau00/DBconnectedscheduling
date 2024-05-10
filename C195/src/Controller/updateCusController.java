package Controller;

import ObjectsFromERD.Country;
import ObjectsFromERD.Customer;
import ObjectsFromERD.FirstDiv;
import helper.CountryDAOImp;
import helper.CustomerDAOImp;
import helper.FirstDivDAOImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.AlertError;
import sample.Navigate;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

public class updateCusController {
    Customer customer;
    FirstDiv startingDiv;
    Country startingCountry;
    CountryDAOImp getCountry = new CountryDAOImp();
    FirstDivDAOImp getfld = new FirstDivDAOImp();
    CustomerDAOImp cusDAO = new CustomerDAOImp();
    Country selectedCountry=null;
    FirstDiv selectedFirstDiv=null;
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    AlertError lambdaAlert = (header, content, error) -> {
        AlertError.alert.setHeaderText(header);
        AlertError.alert.setContentText(content);
        AlertError.alert.setTitle(error);
        AlertError.alert.showAndWait();};
    @FXML
    private Button Add_Update_btn;

    @FXML
    private Button Cancel_btn;

    @FXML
    private ComboBox<Country> CountryCombo;

    @FXML
    private TextField addressTF;

    @FXML
    private TextField cus_idTF;

    @FXML
    private ComboBox<FirstDiv> fldCombo;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField phoneTF;

    @FXML
    private TextField postalTF;

    Stage stage;
    Parent scene;
    @FXML
    public void sendCustomer(Customer customer) throws SQLException {
        this.customer = customer;
        cus_idTF.setText(Integer.toString(customer.getCustomer_ID()));
        nameTF.setText(customer.getCustomer_name());
        addressTF.setText(customer.getAddress());
        postalTF.setText(customer.getPostal_code());
        phoneTF.setText(customer.getPhone());
        selectedFirstDiv = getfld.get(customer.getDivision_id());
        selectedCountry = getCountry.get(selectedFirstDiv.getCountry_ID());
        CountryCombo.getSelectionModel().select(selectedCountry);
        fldCombo.setItems(getfld.getCountry(selectedCountry));
        fldCombo.getSelectionModel().select(selectedFirstDiv);
        CountryCombo.setItems(getCountry.getAll());

    }
    @FXML
    public void initialize() throws SQLException {
    }
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        Navigate.cancel("../View/Main menu.fxml",actionEvent);
    }

    public void onActionUpdate(ActionEvent actionEvent) throws SQLException, IOException {
        String name = nameTF.getText();
        String address = addressTF.getText();
        String phone = phoneTF.getText();
        String postal = postalTF.getText();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (selectedFirstDiv==(null)){
            lambdaAlert.displayAlert("","Please Select A First Level Division","ERROR!");
        }
        else{
        this.customer.setCustomer_name(name);
        this.customer.setAddress(address);
        this.customer.setPostal_code(postal);
        this.customer.setPhone(phone);
        this.customer.setLast_update(timestamp);
        this.customer.setLast_updated_by(Login.user.getUser_name());

        this.customer.setDivision_id(selectedFirstDiv.getId());

        cusDAO.update(this.customer);
        Navigate.add(actionEvent);
        }

    }
    @FXML
    void onActionCountryCombo(ActionEvent event) throws SQLException {
        if (CountryCombo.getValue().equals(selectedCountry)){
            System.out.println("test");
        }
        else{
            System.out.println("test1");
            selectedCountry = CountryCombo.getValue();
            selectedFirstDiv = null;
            fldCombo.setItems(getfld.getCountry(selectedCountry));
            fldCombo.setPromptText("First-Level-Division");

        }

    }

    public void onActionfldCombo(ActionEvent actionEvent) {
        selectedFirstDiv = fldCombo.getValue();
    }
}
