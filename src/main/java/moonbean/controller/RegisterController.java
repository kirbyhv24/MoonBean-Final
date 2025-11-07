package moonbean.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import moonbean.app.AppContext;
import moonbean.app.Navigator;
import moonbean.model.User;

public class RegisterController {
    @FXML private TextField firstNameField, lastNameField, emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    @FXML
    private void onRegister() {
        User u = new User(emailField.getText(), passwordField.getText(),
                firstNameField.getText(), lastNameField.getText());
        boolean ok = AppContext.userService.register(u);

        if (ok) {
            messageLabel.setText("Registration successful! You can now log in.");
        } else {
            messageLabel.setText("Email already exists or fields are empty.");
        }
    }

    @FXML private void onBack() { Navigator.go("login"); }
}
