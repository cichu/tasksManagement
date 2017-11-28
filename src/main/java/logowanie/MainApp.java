package logowanie;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    /**
     * PATH to the location from which program starts
     * to create proper path to resource add packages
     * divided by / with the file name with extension
     * at the end
     */
    public static String PATH = "file:/" + System.getProperty("user.dir") + "\\src\\main\\java\\";

    private Register register;
    private LoginForm loginForm;
    private Menu mainMenu;
    private Stage primaryStage;

    private Uzytkownicy loggedUser;

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        this.showLoginForm();
    }

    public void showLoginForm() {
        if (this.loginForm == null) {
            this.createLoginPage();
        }

        this.hideAll();
        this.loginForm.start(new Stage());
    }

    public void showRegisterForm() {
        if (this.register == null) {
            this.createRegisterForm();
        }

        this.hideAll();
        this.register.start(new Stage());
    }

    public void showMainMenu() {
        if (this.mainMenu == null) {
            this.createMainMenu();
        }

        this.hideAll();
        this.mainMenu.start(new Stage());
    }

    private void createLoginPage() {
        this.loginForm = new LoginForm();
        this.loginForm.setMainApp(this);
    }

    private void createRegisterForm() {
        this.register = new Register();
        this.register.setMainApp(this);
    }

    private void createMainMenu() {
        this.mainMenu = new Menu();
        this.mainMenu.setMainApp(this);
    }

    private void hideAll() {
        if (this.loginForm != null) {
            this.loginForm.hide();
        }
        if (this.register != null) {
            this.register.hide();
        }
        if (this.mainMenu != null) {
            this.mainMenu.hide();
        }
    }

    public void setLoggedUser(Uzytkownicy user) {
        this.loggedUser = user;
    }

    public Register getRegister() {
        return this.register;
    }
}
