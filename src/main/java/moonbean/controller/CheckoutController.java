package moonbean.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import moonbean.app.AppContext;
import moonbean.app.Navigator;
import moonbean.model.Order;

public class CheckoutController {

    @FXML
    private VBox container;

    @FXML
    private Button confirmButton;

    @FXML
    private Button cancelButton;

    @FXML
    private void onConfirmCheckout() {
        try {
            Order order = AppContext.orderService.placeOrder();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Checkout Successful");
            a.setHeaderText("Order placed successfully!");
            a.setContentText("Order ID: " + order.getOrderId() +
                    "\nTotal: ₱" + order.getTotalAmount());
            a.showAndWait();

            Navigator.go("menu");
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Checkout Failed");
            a.setHeaderText("Unable to place order");
            a.setContentText(e.getMessage());
            a.showAndWait();
        }
    }

    @FXML
    private void onCancel() {
        Navigator.go("cart");
    }
}
