package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LogInController{

    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");

    // Import the application's controls
    @FXML
    private Label invalidLoginCredentials;
    @FXML
    private Label invalidSignupCredentials;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField loginUsernameTextField;
    @FXML
    private TextField loginPasswordPasswordField;
    @FXML
    private TextField signUpUsernameTextField;
    @FXML
    private TextField signUpEmailTextField;
    @FXML
    private TextField signUpPasswordPasswordField;
    @FXML
    private TextField signUpRepeatPasswordPasswordField;
    @FXML
    private DatePicker signUpDateDatePicker;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    RadioButton selected;
    ToggleGroup toggleGroup = new ToggleGroup();

    // Creation of methods which are activated on events in the forms
    @FXML
    protected void onCancelButtonClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onLoginButtonClick() {
        if (loginUsernameTextField.getText().isBlank() || loginPasswordPasswordField.getText().isBlank()) {
            invalidLoginCredentials.setText("The Login fields are required!");
            invalidLoginCredentials.setStyle(errorMessage);
            invalidSignupCredentials.setText("");

            if (loginUsernameTextField.getText().isBlank()) {
                loginUsernameTextField.setStyle(errorStyle);
            } else if (loginPasswordPasswordField.getText().isBlank()) {
                loginPasswordPasswordField.setStyle(errorStyle);
            }
            return;
        }
        if(validateCredentials(loginUsernameTextField.getText(),loginPasswordPasswordField.getText())) {
            invalidLoginCredentials.setText("Login Successful!");
// TODO: 10/01/2023 Add root for the next screen. 
            invalidLoginCredentials.setStyle(successMessage);
            loginUsernameTextField.setStyle(successStyle);
            loginPasswordPasswordField.setStyle(successStyle);
            invalidSignupCredentials.setText("");
        }else{
            loginUsernameTextField.setStyle(errorStyle);
            loginPasswordPasswordField.setStyle(errorStyle);
            loginUsernameTextField.setText("");
            loginPasswordPasswordField.setText("");
        }
    }

    @FXML
    protected void onSignUpButtonClick() {

        male.setToggleGroup(toggleGroup);
        female.setToggleGroup(toggleGroup);
        // Set one of the radio buttons as the default selection
        male.setSelected(true);
        // Create a horizontal box to hold the radio buttons
        HBox hbox = new HBox(male, female);
        toggleGroup.selectedToggleProperty().addListener((ov, oldToggle, newToggle) -> {
            selected = (RadioButton) newToggle;
        });
        String username = signUpUsernameTextField.getText();
        String password = signUpPasswordPasswordField.getText();
        String repeatPassword = signUpRepeatPasswordPasswordField.getText();
        String email = signUpEmailTextField.getText();

        if(username.isBlank() || email.isBlank() || password.isBlank() || repeatPassword.isBlank()){
            invalidSignupCredentials.setText("Please fill in all fields!");
            invalidSignupCredentials.setStyle(errorMessage);
            invalidLoginCredentials.setText("");
            if (username.isBlank()) {
                signUpUsernameTextField.setStyle(errorStyle);
            } else if (email.isBlank()) {
                signUpEmailTextField.setStyle(errorStyle);
            } else if (password.isBlank()) {
                signUpPasswordPasswordField.setStyle(errorStyle);
            } else if (repeatPassword.isBlank()) {
                signUpRepeatPasswordPasswordField.setStyle(errorStyle);
            }
            return;
        }
        if(!validateEmail(email)){
            invalidSignupCredentials.setText("Please add a valid email");
            invalidSignupCredentials.setStyle(errorMessage);
            signUpEmailTextField.setStyle(errorStyle);
            invalidLoginCredentials.setText("");
            return;
        }
        if(!password.equals(repeatPassword)){
            invalidSignupCredentials.setText("The Passwords don't match!");
            invalidSignupCredentials.setStyle(errorMessage);
            signUpPasswordPasswordField.setStyle(errorStyle);
            signUpRepeatPasswordPasswordField.setStyle(errorStyle);
            invalidLoginCredentials.setText("");
            return;
        }
        if(!isStrongPassword(password)){
            invalidSignupCredentials.setText("Please add a valid password! ( Must Contains ...) ");
            invalidSignupCredentials.setStyle(errorMessage);
            signUpPasswordPasswordField.setStyle(errorStyle);
            signUpRepeatPasswordPasswordField.setStyle(errorStyle);
            invalidLoginCredentials.setText("");
            return;
        }
        if(!addNewUser(username,password,email,signUpDateDatePicker.getValue(),selected.getText() == "Male" ? "Male" : "Female")) {
            invalidSignupCredentials.setText("Something went wrong in the process, please try again!");
            invalidSignupCredentials.setStyle(errorMessage);
            signUpPasswordPasswordField.setStyle(errorStyle);
            signUpRepeatPasswordPasswordField.setStyle(errorStyle);
            invalidLoginCredentials.setText("");
            return;
        }
        invalidSignupCredentials.setText("Signed up successfully !!");
        invalidSignupCredentials.setStyle(successMessage);
        signUpUsernameTextField.setStyle(successStyle);
        signUpEmailTextField.setStyle(successStyle);
        signUpPasswordPasswordField.setStyle(successStyle);
        signUpRepeatPasswordPasswordField.setStyle(successStyle);
        invalidLoginCredentials.setText("");

    }

    private boolean addNewUser(String username, String password, String email, LocalDate value, String s) {
        // TODO: 10/01/2023 Post request to add the user to the system, and return true if the process was done successfully.
        return true;
    }

    private boolean isStrongPassword(String password) {
        // TODO: 10/01/2023 Check if the password is strong - valid.
        return true;
    }

    private boolean validateCredentials(String username, String password) {
        // TODO: 10/01/2023 Create a validation method which ask the server for the credentials of the user.
        return true;
    }

    private boolean validateEmail(String mail){
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern).matcher(mail).matches();
    }


}