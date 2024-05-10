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

public class addCusController {
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

    /**
     * This method initializes add customer controller
     * <p>
     *   This method takes no arguments and returns nothing.
     * </p>**/
    @FXML
    public void initialize() throws SQLException {
        CountryCombo.setItems(getCountry.getAll());
    }
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        Navigate.cancel("../View/Main menu.fxml",actionEvent);
    }
    /**
     * This method adds the new customer to the database.
     * <p>
     *    This method takes the event sent from the button. The only input validation is on first division.
     * </p>**/
    public void onActionAdd(ActionEvent actionEvent) throws SQLException, IOException {
        String name = nameTF.getText();
        String address = addressTF.getText();
        String phone = phoneTF.getText();
        String postal = postalTF.getText();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (selectedFirstDiv==(null)){
            lambdaAlert.displayAlert("","Please Select A First Level Division","ERROR!");
        }
        else{
            System.out.println(selectedFirstDiv.getId());
            cusDAO.insert(new Customer(name,address,phone,postal,timestamp,Login.user.getUser_name(),timestamp,Login.user.getUser_name(), selectedFirstDiv.getId()));

        }
        Navigate.add(actionEvent);
    }
    @FXML
    void onActionCountryCombo(ActionEvent event) throws SQLException {
        if (CountryCombo.getValue().equals(selectedCountry)){
        }
        else{
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
