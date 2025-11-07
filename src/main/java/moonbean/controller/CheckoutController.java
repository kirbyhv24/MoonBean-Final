package moonbean.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import moonbean.app.AppContext;
import moonbean.app.Navigator;
import moonbean.model.CartItem;

/**
 * Handles checkout flow — shows order summary, confirms purchase,
 * and clears the cart after checkout.
 */
public class CheckoutController {

    @FXML private TableView<CartItem> table;
    @FXML private TableColumn<CartItem, String> nameCol;
    @FXML private TableColumn<CartItem, Number> qtyCol;
    @FXML private TableColumn<CartItem, Number> priceCol;
    @FXML private TableColumn<CartItem, Number> subtotalCol;
    @FXML private Label totalLabel;

    @FXML
    private void initialize() {
        // setup columns
        nameCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getProduct().getName()));
        qtyCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleIntegerProperty(c.getValue().getQuantity()));
        priceCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleDoubleProperty(c.getValue().getProduct().getPrice()));
        subtotalCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleDoubleProperty(c.getValue().getSubtotal()));

        // populate table + total
        table.setItems(FXCollections.observableArrayList(AppContext.cartService.getItems()));
        totalLabel.setText(String.format("Total: ₱%.2f", AppContext.cartService.getTotal()));
    }

    @FXML
    private void onCheckout() {
        if (AppContext.cartService.getItems().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Your cart is empty. Add items before checking out!");
            return;
        }

        double total = AppContext.cartService.getTotal();
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Purchase");
        confirm.setHeaderText("Confirm your purchase");
        confirm.setContentText(String.format("Proceed with payment of ₱%.2f?", total));

        var result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Deduct product stock based on cart quantities
            for (var item : AppContext.cartService.getItems()) {
                var p = item.getProduct();
                int newStock = p.getStock() - item.getQuantity();
                if (newStock < 0) newStock = 0; // just in case
                p.setStock(newStock);
            }

            // Clear cart after purchase
            AppContext.cartService.clear();

            showAlert(Alert.AlertType.INFORMATION,
                    "Purchase successful! Thank you for ordering at MoonBean Café!");

            // Return to menu
            Navigator.go("menu");
        }
    }

    @FXML
    private void onCancel() {
        Navigator.go("cart");
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert a = new Alert(type);
        a.setTitle("Message");
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
