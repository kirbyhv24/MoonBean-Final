package moonbean.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class Navigator {
    private Navigator() {}

    public static void go(String viewName) {
        try {
            var url = Navigator.class.getResource("/views/" + viewName + ".fxml");
            if (url == null) throw new IllegalStateException("Missing FXML: " + viewName);
            Stage stage = AppContext.getPrimaryStage();
            stage.setScene(new Scene(FXMLLoader.load(url), 800, 520));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
