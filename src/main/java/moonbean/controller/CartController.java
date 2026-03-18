package moonbean.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import moonbean.app.AppContext;
import moonbean.app.Navigator;
import moonbean.model.CartItem;

public class CartController {

    @FXML private TableView<CartItem> cartTable;
    @FXML private TableColumn<CartItem, String> productCol;
    @FXML private TableColumn<CartItem, Number> qtyCol;
    @FXML private TableColumn<CartItem, Number> priceCol;
    @FXML private TableColumn<CartItem, Number> subtotalCol;
    @FXML private Label totalLabel;

    @FXML
    private void initialize() {
        refresh();
    }

    private void refresh() {
        // setup columns
        productCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getProduct().getName()));
        qtyCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleIntegerProperty(c.getValue().getQuantity()));
        priceCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleDoubleProperty(c.getValue().getProduct().getPrice()));
        subtotalCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleDoubleProperty(c.getValue().getSubtotal()));

        // update table + total
        cartTable.setItems(FXCollections.observableArrayList(AppContext.cartService.getItems()));
        totalLabel.setText(String.format("Total: ₱%.2f", AppContext.cartService.getTotal()));
    }

    @FXML
    private void onClearCart() {
        if (AppContext.cartService.getItems().isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Your cart is already empty.");
            return;
        }

        AppContext.cartService.clear();
        refresh();
        showAlert(Alert.AlertType.INFORMATION, "Cart cleared successfully!");
    }

    @FXML
    private void onCheckout() {
        if (AppContext.cartService.getItems().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Your cart is empty. Add items before checking out!");
            return;
        }

        Navigator.go("checkout");
    }

    @FXML
    private void onBack() {
        Navigator.go("menu");
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert a = new Alert(type);
        a.setTitle("Message");
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
