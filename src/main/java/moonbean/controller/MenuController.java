package moonbean.controller;

import javafx.fxml.FXML;
import moonbean.app.Navigator;

public class MenuController {

    @FXML private void onBrowseProducts() {
        Navigator.go("products");
    }

    @FXML private void onViewCart() {
        Navigator.go("cart");
    }

    @FXML private void onLogout() {
        Navigator.go("login");
    }
}
