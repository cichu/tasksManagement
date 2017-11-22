package logowanie;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;


public class Register extends Application
{
    private MainApp mainApp;

    private Text formTitle, notification;
    private Label userMail, userPass;
    private TextField userMailField;
    private PasswordField userPassField;
    private Label userFirstName, userLastName;
    private TextField userFirstNameField;
    private TextField userLastNameField;
    //	Uzytkownicy o1=new Uzytkownicy();
    ArrayList<Uzytkownicy> lista = new ArrayList<Uzytkownicy>();

    // temporary

    private Stage primaryStage;

    @Override
    public void start (Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Javafx Login Form");

        this.initStage();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    private void logIn() {
        //if(userLoginField.getText().equals(login)&& userPassField.getText().equals(password)) {
//			this.login=userLoginField.getText();
        Uzytkownicy o1 = new Uzytkownicy();
        o1.firstName = userFirstNameField.getText();
        o1.lastName = userLastNameField.getText();
        o1.mail = userMailField.getText();
        o1.password = userPassField.getText();
        lista.add(o1);
        System.out.println(lista.size());
        System.out.println(lista.get(0).mail);

        this.mainApp.showLoginForm();

        //}
        //	else {
        //notification.setText("Zly login");
        //	}
    }

    /*private void openLoginForm() {

    }*/

    private void initStage() {
        // prepare grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        formTitle = new Text("Welcome");
        grid.add(formTitle, 0, 0,2,1);

        userMail = new Label("user Mail: ");
        grid.add(userMail, 0, 1);

        userMailField = new TextField();
        grid.add(userMailField,1,1);

        userPass = new Label("user password:");
        grid.add(userPass, 0, 2);

        userPassField = new PasswordField();
        grid.add(userPassField, 1, 2);

        userFirstName = new Label("user first Name: ");
        grid.add(userFirstName, 0, 3);

        userFirstNameField = new TextField();
        grid.add(userFirstNameField,1,3);

        userLastName = new Label("user Last Name:");
        grid.add(userLastName, 0, 4);

        userLastNameField = new TextField();
        grid.add(userLastNameField, 1, 4);


        Button registerButton = new Button("Register");
        registerButton.setOnAction(event -> logIn());
        GridPane.setHalignment(registerButton, HPos.LEFT);
        grid.add(registerButton, 1, 5);

        notification = new Text();
        grid.add(notification,1,6);

        Scene scene = new Scene(grid, 300, 275);
        scene.getStylesheets() .add(getClass().getResource("application.css").toExternalForm());

        // Pressing Enter works as pressing button "Sign in"
        grid.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.logIn();
            }
        });

        this.primaryStage.setMinHeight(275);
        this.primaryStage.setMinWidth(300);

        primaryStage.setScene(scene);
        primaryStage.show();
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


class Uzytkownicy
{
    public String mail;
    public String password;
    public String firstName;
    public String lastName;
}
