package logowanie;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Register register;
    private LoginForm loginForm;
    private Stage primaryStage;

    private Uzytkownicy loggedUser;

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.register = new Register();
        this.register.setMainApp(this);
        this.register.start(this.primaryStage);
    }

    public void showLoginPage() {
        if (this.loginForm == null) {
            this.createLoginPage();
        }

        this.loginForm.start(new Stage());
    }

    private void createLoginPage() {
        this.loginForm = new LoginForm();
        this.loginForm.setMainApp(this);
    }

    public void setLoggedUser(Uzytkownicy user) {
        this.loggedUser = user;
    }

    public Register getRegister() {
        return this.register;
    }
}
