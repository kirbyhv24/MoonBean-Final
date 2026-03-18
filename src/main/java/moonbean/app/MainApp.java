package moonbean.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        AppContext.setPrimaryStage(stage);

        Scene scene = new Scene(
                FXMLLoader.load(getClass().getResource("/views/login.fxml")),
                800, 520
        );

        stage.setTitle("MoonBean Café");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
