package moonbean.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import moonbean.app.AppContext;
import moonbean.app.Navigator;
import moonbean.model.Product;

public class ProductsController {

    @FXML private TableView<Product> table;
    @FXML private TableColumn<Product, String> nameCol;
    @FXML private TableColumn<Product, String> categoryCol;
    @FXML private TableColumn<Product, Number> priceCol;
    @FXML private TableColumn<Product, Number> stockCol;

    @FXML
    private void initialize() {
        // setup columns
        nameCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getName()));
        categoryCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getCategory()));
        priceCol.setCellValueFactory(c -> new javafx.beans.property.SimpleDoubleProperty(c.getValue().getPrice()));
        stockCol.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getStock()));

        // load initial data
        table.setItems(FXCollections.observableArrayList(AppContext.productService.findAll()));
    }

    @FXML
    private void onAddToCart() {
        Product selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a product to add to cart.");
            return;
        }

        AppContext.cartService.add(selected.getId(), 1);
        showAlert(Alert.AlertType.INFORMATION, "Added to Cart",
                "Added 1 × " + selected.getName() + " to your cart.");
    }

    @FXML
    private void onSortPriceAsc() {
        table.setItems(FXCollections.observableArrayList(
                AppContext.productService.sortByPriceAsc(table.getItems())
        ));
    }

    @FXML
    private void onBack() {
        Navigator.go("menu");
    }

    // small alert helper
    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert a = new Alert(type);
        a.setTitle("Message");
        a.setHeaderText(title);
        a.setContentText(msg);
        a.showAndWait();
    }
}
