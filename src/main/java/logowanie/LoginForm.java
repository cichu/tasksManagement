package logowanie;

import org.hibernate.Session;

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
import repository.HibernateUtil;
import repository.UserRepository;
import javafx.scene.Group;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.File;


public class LoginForm extends Application
{
	private MainApp mainApp;

	private Text formTitle, notification;
	private Label userLogin, userPass;
	private TextField userLoginField;
	private PasswordField userPassField;

	// temporary
	private String login="Dawid";
	private String password="Krawczyk";

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

	private void logIn()
	{
		Register register = this.mainApp.getRegister();
		//Uzytkownicy user = this.findUserByNameOrEmail(this.userLoginField.getText());

		//if (user == null) {
			// User not found
	//		this.notification.setText("Nie znaleziono takiego uĹĽytkownika");
//		}  
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		UserRepository userRepo = new UserRepository(session);
		//userRepo.deleteUser(5L);
    	String s=userRepo.getUserPassword(userLoginField.getText());
    	System.out.println(s);
		if (this.userPassField.getText().equals(s)) {
			// User logged successfully
			this.notification.setText("Zalogowano");
//			this.mainApp.setLoggedUser(user);
			this.mainApp.showMainMenu();
		} else {
			// User password does not match
			this.notification.setText("Złe hasło, lub login");
		}

		/*
		System.out.println(register.lista.get(0).mail);
		if (userLoginField.getText().equals(login)&& userPassField.getText().equals(password)) {
			this.openMenu();
		}
		else {
			notification.setText("Zly login");
		} */
	}
	private void register()
	{
		this.openRegister();
	}

	private void openMenu() {
		Menu menu = new Menu();
		this.primaryStage.close();
		menu.start(new Stage());
	}

	private void openRegister() {
		this.mainApp.showRegisterForm();
	}

	private void initStage() {
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\logowanie\\";
		// prepare grid
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(5));
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		final ImageView imv = new ImageView();
        final Image image2 = new Image(new File(path + "2.png").toURI().toString());
        imv.setImage(image2);

        final HBox pictureRegion = new HBox();
        pictureRegion.getChildren().add(imv);
		formTitle = new Text("Welcome");
		//grid.add(pictureRegion, 0, 0,2,1);

		userLogin = new Label("user login: ");
		grid.add(userLogin, 0, 1);

		userLoginField = new TextField();
		grid.add(userLoginField,1,1);

		userPass = new Label("user password:");
		grid.add(userPass, 0, 2);

		userPassField = new PasswordField();
		grid.add(userPassField, 1, 2);

		Button signInButton = new Button("Sign in");
		signInButton.setOnAction(event -> logIn());
		GridPane.setHalignment(signInButton, HPos.RIGHT);
		grid.add(signInButton, 1, 4);

		Button registerButton = new Button("Register");
		registerButton.setOnAction(event -> register());
		GridPane.setHalignment(registerButton, HPos.LEFT);
		grid.add(registerButton, 1, 4);

		notification = new Text();
		grid.add(notification,1,6);

		Scene scene = new Scene(grid, 300, 275);
		scene.getStylesheets().add(new File(path + "application.css").toURI().toString());

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

	private Uzytkownicy findUserByNameOrEmail(String nameOrEmail) {
		Register register = this.mainApp.getRegister();

		for (Uzytkownicy user : register.lista) {
			if (user.firstName.equals(nameOrEmail)) {
				return user;
			}

			if (user.mail.equals(nameOrEmail)) {
				return user;
			}
		}

		// returns null if no matching firstName or email is found
		return null;
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
