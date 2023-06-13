package src.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import src.database.UserAuthentication;
import src.localization.Language;
import src.vehicleData.VehicleCollection;

public class AuthController {
    @FXML
    public TextField tf_login;
    @FXML
    public Label label_login;
    @FXML
    public Button Button_signUp;
    @FXML
    public Button Button_login;
    @FXML
    public PasswordField pf_password;
    private boolean registration = false;
    @FXML
    void initialize() {
        updateLanguage();
    }
    @FXML
    void setButton_login() {
        tf_login.setPromptText(GUI.getAppLanguage().getString("login"));
        pf_password.setPromptText(GUI.getAppLanguage().getString("password"));
        if (registration) {
            if (UserAuthentication.userRegistration(tf_login.getText().trim(), pf_password.getText())) {
                tf_login.setText("");
                pf_password.setText("");
                label_login.setText(GUI.getAppLanguage().getString("auth"));
                Button_signUp.setText(GUI.getAppLanguage().getString("become"));
                Button_login.setText(GUI.getAppLanguage().getString("log_in"));
                registration = false;
            } else {
                tf_login.setText("");
                pf_password.setText("");
                tf_login.setPromptText(GUI.getAppLanguage().getString("already"));
            }
        } else {
            if (UserAuthentication.userLoggingIn(tf_login.getText().trim(), pf_password.getText())) {
                Button_login.getScene().getWindow().hide();
                VehicleCollection.putVehiclesFromDB();
                GUI.openMainWindow();
            } else {
                tf_login.setText("");
                pf_password.setText("");
                tf_login.setPromptText(GUI.getAppLanguage().getString("invalid"));
            }
        }
    }
    @FXML
    void setButton_signUp() {
        tf_login.setText("");
        pf_password.setText("");
        tf_login.setPromptText(GUI.getAppLanguage().getString("login"));
        pf_password.setPromptText(GUI.getAppLanguage().getString("password"));
        if (registration) {
            label_login.setText(GUI.getAppLanguage().getString("auth"));
            Button_signUp.setText(GUI.getAppLanguage().getString("become"));
            Button_login.setText(GUI.getAppLanguage().getString("log_in"));
            registration = false;
        } else {
            label_login.setText(GUI.getAppLanguage().getString("reg"));
            Button_signUp.setText(GUI.getAppLanguage().getString("sign_in"));
            Button_login.setText(GUI.getAppLanguage().getString("sign_up"));
            registration = true;
        }
    }
    @FXML
    void setButton_ru() {
        GUI.setAppLanguage(Language.ru);
        updateLanguage();
    }
    @FXML
    void setButton_hu() {
        GUI.setAppLanguage(Language.hu);
        updateLanguage();
    }
    @FXML
    void setButton_mk() {
        GUI.setAppLanguage(Language.mk);
        updateLanguage();
    }
    @FXML
    void setButton_es() {
        GUI.setAppLanguage(Language.es);
        updateLanguage();
    }
    private void updateLanguage() {
        if (registration) {
            label_login.setText(GUI.getAppLanguage().getString("reg"));
            Button_signUp.setText(GUI.getAppLanguage().getString("sign_in"));
            Button_login.setText(GUI.getAppLanguage().getString("sign_up"));
        } else {
            label_login.setText(GUI.getAppLanguage().getString("auth"));
            Button_signUp.setText(GUI.getAppLanguage().getString("become"));
            Button_login.setText(GUI.getAppLanguage().getString("log_in"));
        }
        tf_login.setPromptText(GUI.getAppLanguage().getString("login"));
        pf_password.setPromptText(GUI.getAppLanguage().getString("password"));
    }
}
