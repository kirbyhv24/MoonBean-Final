package moonbean.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import moonbean.app.AppContext;
import moonbean.app.Navigator;
import moonbean.model.User;

public class LoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    private void onLogin() {
        String email = emailField.getText();
        String pass = passwordField.getText();

        User u = AppContext.userService.login(email, pass);
        if (u == null) {
            errorLabel.setText("Invalid email or password.");
            return;
        }

        errorLabel.setText("");
        Navigator.go("menu");
    }

    @FXML private void onRegister() {
        Navigator.go("register");
    }

    @FXML private void onGuest() {
        Navigator.go("menu");
    }
}
