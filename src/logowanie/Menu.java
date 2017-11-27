package logowanie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu extends Application {

    private MainApp mainApp;

    private Stage primaryStage;

    public void start(Stage stage) {
        this.initStage(stage);
    }

    private void initStage(Stage stage) {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Menu");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Menu.class.getResource("/logowanie/layout.fxml"));

        AnchorPane root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 1000,800);

        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void hide() {
        if (this.primaryStage != null) {
            this.primaryStage.hide();
        }
    }
}
