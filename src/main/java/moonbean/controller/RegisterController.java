package moonbean.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import moonbean.app.AppContext;
import moonbean.app.Navigator;
import moonbean.service.UserService;

public class RegisterController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    private final UserService userService = AppContext.userService;

    @FXML
    private void onRegister() {
        String first = firstNameField.getText().trim();
        String last = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String pass = passwordField.getText().trim();

        if (first.isEmpty() || last.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please fill in all fields.");
            return;
        }

        boolean success = userService.register(first, last, email, pass);
        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Account created successfully!");
            Navigator.go("login");
        } else {
            showAlert(Alert.AlertType.ERROR, "Email already exists.");
        }
    }

    // ✅ This fixes your error
    @FXML
    private void onBackToLogin() {
        Navigator.go("login");
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
