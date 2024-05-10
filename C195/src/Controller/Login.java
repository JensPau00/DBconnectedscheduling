package Controller;

import ObjectsFromERD.User;
import com.mysql.cj.log.Log;
import helper.UsersDAO;

import java.io.*;

import helper.UsersDAOImp;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.AlertError;
import sample.Main;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Observable;

public class Login {
    public static FileWriter out;
    Stage stage;
    public UsersDAOImp users = new UsersDAOImp();
    static User user;

    public Text passwordText;
    String language;
    public Text userNameText;
    AlertError lambdaAlert = (header, content, error) -> {
        AlertError.alert.setHeaderText(header);
        AlertError.alert.setContentText(content);
        AlertError.alert.setTitle(error);
        AlertError.alert.showAndWait();};

    @FXML
    private Button exitButton;

    @FXML
    private Button loginButton;
    private Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
    @FXML
    private PasswordField passwordField;
    ButtonType okFR = new ButtonType("D'ACCORD", ButtonBar.ButtonData.OK_DONE);

    @FXML
    private TextField userNameTextField;

    @FXML
    private Label zoneIDLabel;
    /**
     * This constructor creates the file reader and writer. It also rewrites all of the previous information to avoid losing information on the login activity file.
     * <p>
     *    This constructor doesn't use any user input and returns nothing.
     * </p>**/
    public Login() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("login_activity.txt"));
        String line;
        String file="";
        while((line = in.readLine())!=null){
            file+=line+"\n";
        }
        out = new FileWriter("login_activity.txt");
        out.write(file);

    }
    /**
     * initalize is called as soon as the scene is created. This Sets the Zone and language.
     * <p>
     * The users computer settings change the language of the login screen this returns nothing.
     * </p>**/
    @FXML
    void initialize(){
        String zone = String.valueOf(ZoneId.systemDefault());
        Locale lang= Locale.getDefault();
        language = lang.getLanguage();
        System.out.println(language);
        zoneIDLabel.setText((zone));
        if (language.equals("fr")){
            userNameText.setText("nom d'utilisateur");
            passwordText.setText("mot de passe");
            loginButton.setText("se Connecter");
            exitButton.setText("Sortie");
        }
    }
    @FXML
    void OnActionExitApplication(ActionEvent event) throws IOException {
        if (language.equals("fr")){
            confirm.setTitle("confirmer");
            confirm.setHeaderText("");
            ((Button)confirm.getDialogPane().lookupButton(ButtonType.OK)).setText("D'ACCORD");
            ((Button)confirm.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Annuler");


            confirm.setContentText("Souhaitez-vous sortir?");
        }
        else{
            confirm.setHeaderText("");
            confirm.setContentText("Would you like to exit?");
        }

        confirm.showAndWait();
        if (confirm.getResult()==ButtonType.OK){
            Platform.exit();
            out.close();
        }
    }

    @FXML
    void OnActionLogin(ActionEvent event) throws SQLException, IOException {
        String user = userNameTextField.getText();
        String pass = passwordField.getText();
        UsersDAOImp userNameQuery = new UsersDAOImp();
        String isUser=userNameQuery.select(user,pass);
        if (isUser.equals("pass")){
            if(language.equals("fr")){
                lambdaAlert.displayAlert("Utilisateur invalide", "Le nom d'utilisateur et le mot de passe ne correspondent pas.","erreur"
                );
            }
            else{
                lambdaAlert.displayAlert("INVALID USER","The UserName and Password do not match.","error");
            }
            out.append("Username = "+user+",Password = "+pass+",Attempt time = "+LocalDate.now()+ Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")))+", Attempt = Failure\n");

        }
        else if (isUser.equals("user")){
            if(language.equals("fr")){
                lambdaAlert.displayAlert("Utilisateur invalide", "Le nom d'utilisateur n'existe pas.","erreur"
                );
            }
            else{
                lambdaAlert.displayAlert("INVALID USER","username does not exist.","error");
            }
            out.append("Username = "+user+", Password = "+pass+", Attempt time = "+LocalDate.now()+ Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")))+", Attempt = Failure\n");
        }
        else{
            out.append("Username = "+user+", Password = "+pass+", Attempt time = "+LocalDate.now()+ Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")))+", Attempt = Success\n");
            System.out.print("Username = "+user+", Password = "+pass+", Attempt time = "+LocalDate.now()+ Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
            out.close();
            Login.user = users.selectUser(user);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load((Main.class.getResource("../View/Main menu.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

}
