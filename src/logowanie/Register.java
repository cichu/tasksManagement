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

public class Register extends Application {
	private MainApp mainApp;

	private Text formTitle, notification;
	private Label userMail, userPass, userPassCorrect;
	private TextField userMailField;
	private PasswordField userPassField, userPassFieldCorrect;
	private Label userFirstName, userLastName;
	private TextField userFirstNameField;
	private TextField userLastNameField;
	// Uzytkownicy o1=new Uzytkownicy();
	ArrayList<Uzytkownicy> lista = new ArrayList<Uzytkownicy>();

	// temporary

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Javafx Login Form");

		this.initStage();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void logIn() {
		// if(userLoginField.getText().equals(login)&&
		// userPassField.getText().equals(password)) {
		// this.login=userLoginField.getText();
		Uzytkownicy o1 = new Uzytkownicy();
		o1.firstName = userFirstNameField.getText();
		o1.lastName = userLastNameField.getText();
		o1.mail = userMailField.getText();
		o1.password = userPassField.getText();
		if (checkAll(o1) == true) {
			lista.add(o1);
			System.out.println(lista.size());
			System.out.println(lista.get(0).mail);

			this.mainApp.showLoginForm();
		}
		// }
		// else {
		// notification.setText("Zly login");
		// }
	}

	private void back() {
		this.mainApp.showLoginForm();
	}

	/*
	 * private void openLoginForm() {
	 * 
	 * }
	 */

	private void initStage() {
		// prepare grid
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(40, 40, 40, 40));

		formTitle = new Text("Welcome");
		grid.add(formTitle, 0, 0, 2, 1);

		userMail = new Label("user Mail: ");
		grid.add(userMail, 0, 1);

		userMailField = new TextField();
		grid.add(userMailField, 1, 1);

		userPass = new Label("user password:");
		grid.add(userPass, 0, 2);

		userPassField = new PasswordField();
		grid.add(userPassField, 1, 2);

		userPassCorrect = new Label("user password confirmed:");
		grid.add(userPassCorrect, 0, 3);

		userPassFieldCorrect = new PasswordField();
		grid.add(userPassFieldCorrect, 1, 3);

		userFirstName = new Label("user first Name: ");
		grid.add(userFirstName, 0, 4);

		userFirstNameField = new TextField();
		grid.add(userFirstNameField, 1, 4);

		userLastName = new Label("user Last Name:");
		grid.add(userLastName, 0, 5);

		userLastNameField = new TextField();
		grid.add(userLastNameField, 1, 5);

		Button registerButton = new Button("Register");
		registerButton.setOnAction(event -> logIn());
		GridPane.setHalignment(registerButton, HPos.RIGHT);
		grid.add(registerButton, 1, 6);
		Button backButton = new Button("Back");
		backButton.setOnAction(event -> back());
		GridPane.setHalignment(backButton, HPos.LEFT);
		grid.add(backButton, 1, 6);

		notification = new Text("\n\n\n\n\n");
		grid.add(notification, 1, 8);

		Scene scene = new Scene(grid, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

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

	public static boolean checkName(Uzytkownicy o1) {
		if (o1.firstName.length() < 3) {
			return false;
		}
		for (int i = 0; i < o1.firstName.length(); i++) {
			if (o1.firstName.charAt(i) >= 65 && o1.firstName.charAt(i) <= 90) {

			} else if (o1.firstName.charAt(i) >= 97 && o1.firstName.charAt(i) <= 122) {

			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean checkLastName(Uzytkownicy o1) {
		if (o1.lastName.length() < 3) {
			return false;
		}
		for (int i = 0; i < o1.lastName.length(); i++) {
			if (o1.lastName.charAt(i) >= 65 && o1.lastName.charAt(i) <= 90) {

			} else if (o1.lastName.charAt(i) >= 97 && o1.lastName.charAt(i) <= 122) {

			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean checkMail(Uzytkownicy o1) {
		boolean tempMonkey = false;
		boolean tempDot = false;
		for (int i = 0; i < o1.mail.length(); i++) {
			if (o1.mail.charAt(i) <= 32 && o1.mail.charAt(i) >= 122) {
				return false;
			} else if (o1.mail.charAt(i) == 64)
				tempMonkey = true;
			else if (o1.mail.charAt(i) == 46)
				tempDot = true;
		}
		if (tempMonkey == true && tempDot == true)
			return true;
		else
			return false;
	}

	public static boolean checkPassword(Uzytkownicy o1) {
		boolean tempBigChar = false;
		boolean tempSmallChar = false;
		boolean tempDigitChar = false;
		if (o1.password.length() < 8) {
			return false;
		}
		for (int i = 0; i < o1.password.length(); i++) {
			if (o1.password.charAt(i) >= 65 && o1.password.charAt(i) <= 90) {
				tempSmallChar = true;
			} else if (o1.password.charAt(i) >= 97 && o1.password.charAt(i) <= 122) {
				tempBigChar = true;
			} else if (o1.password.charAt(i) >= 48 && o1.password.charAt(i) <= 57) {
				tempDigitChar = true;
			} else {
				return false;
			}
		}
		if (tempSmallChar == true && tempBigChar == true && tempDigitChar == true) {
			return true;
		} else {
			return false;
		}
	}

	boolean checkPasswordCorrect() {
		if (userPassField.getText() == userPassFieldCorrect.getText())
			return true;
		else
			return false;
	}

	boolean checkAll(Uzytkownicy o1) {
		int c = 5;
		StringBuilder builder = new StringBuilder("");
		if (checkPasswordCorrect() == false) {
			builder.append("Has³a siê nie zgadzaj¹\n");
			c--;
		}
		if (checkPassword(o1) == false) {
			builder.append("").append("Z³e has³o\n");
			c--;
		}
		if (checkMail(o1) == false) {
			builder.append("Z³y mail\n");
			c--;
		}
		if (checkName(o1) == false) {
			builder.append("Z³e imie\n");
			c--;
		}
		if (checkLastName(o1) == false) {
			builder.append("Z³e nazwisko\n");
			c--;
		}
		for (int i = 0; i < c; i++) {
			builder.append("\n");
		}
		if (checkPassword(o1) == true && checkMail(o1) == true && checkName(o1) == true && checkLastName(o1) == true)
			return true;
		else {
			notification.setText(builder.toString());
			return false;
		}
	}
}

class Uzytkownicy {
	public String mail;
	public String password;
	public String firstName;
	public String lastName;
}
