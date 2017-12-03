package logowanie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Menu extends Application {

    private MainApp mainApp;

    private Stage primaryStage;

    public void start(Stage stage) {
        this.initStage(stage);
    }

    private void initStage(Stage stage) {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Menu");

        String path = System.getProperty("user.dir") + "\\src\\main\\java\\logowanie\\";
        System.out.println(new File(path).toURI().toString());
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(new File(path + "layout.fxml").toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        AnchorPane root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 1100,800);

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
