package logowanie;

import org.hibernate.Session;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import repository.HibernateUtil;
import repository.UserRepository;
//import javafx.scene.Group;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
//import javafx.scene.paint.Color;

import java.io.File;
import java.security.NoSuchAlgorithmException;


public class LoginForm extends Application
{
	private MainApp mainApp;
	//private Text formTitle;
	private Text notification;
	private Label userLogin, userPass;
	private TextField userLoginField;
	private PasswordField userPassField;

	// temporary


	private Stage primaryStage;

	@Override
	public void start (Stage primaryStage)
	{
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Javafx Login Form");
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		this.initStage();
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	private void logIn()
	{
//		Register register = this.mainApp.getRegister();
		//Uzytkownicy user = this.findUserByNameOrEmail(this.userLoginField.getText());

		//if (user == null) {
			// User not found
	//		this.notification.setText("Nie znaleziono takiego uĹĽytkownika");
//		}  
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		UserRepository userRepo = new UserRepository(session);
		//userRepo.deleteUser(5L);
		Register register=new Register();
    	String s=userRepo.getUserPassword(userLoginField.getText());
    	System.out.println(s);
		try {
			if (register.sha1(this.userPassField.getText()).equals(s)) {
				// User logged successfully
				this.notification.setText("Zalogowano");
//			this.mainApp.setLoggedUser(user);
				this.mainApp.showMainMenu();
			} else {
				// User password does not match
				this.notification.setText("Złe hasło, lub login");
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

//	private void openMenu() {
//		Menu menu = new Menu();
//		this.primaryStage.close();
//		menu.start(new Stage());
//	}

	private void openRegister() {
		this.mainApp.showRegisterForm();
	}

	private void initStage() {
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\logowanie\\";
		// prepare grid
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(5));
		grid.setHgap(30);
		grid.setVgap(25);
		grid.setPadding(new Insets(40,40,40,40));
		final ImageView imv = new ImageView();
        final Image image2 = new Image(new File(path + "logo_do_logowania2.png").toURI().toString());
        imv.setImage(image2);

        final HBox pictureRegion = new HBox();
        pictureRegion.setAlignment(Pos.TOP_CENTER);
        pictureRegion.getChildren().add(imv);
		//formTitle = new Text("Welcome");
		grid.add(pictureRegion, 0, 0,2,1);

		userLogin = new Label("user login: ");
		grid.add(userLogin, 0, 1);
		userLogin.getStyleClass().add("userLogin");

		userLoginField = new TextField();
		userLoginField.setPromptText("Enter your Login");
		userLoginField.getStyleClass().add("login-field");
		userLoginField.setMinHeight(30);
		userLoginField.setMinWidth(180);
		grid.add(userLoginField,1,1);

		userPass = new Label("user password:");
		grid.add(userPass, 0, 2);
		userPass.getStyleClass().add("userPassword");

		userPassField = new PasswordField();
		userPassField.setPromptText("Enter your Password");
		userPassField.getStyleClass().add("pass-field");
		userPassField.setMinHeight(30);
		userPassField.setMinWidth(180);
		grid.add(userPassField, 1, 2);

		Button signInButton = new Button("Sign in");
		signInButton.getStyleClass().add("button-click");
		signInButton.setOnAction(event -> logIn());
		signInButton.setMinWidth(160);
		signInButton.setMinHeight(35);
		GridPane.setHalignment(signInButton, HPos.LEFT);
		grid.add(signInButton, 0, 4);

		Button registerButton = new Button("Register");
		registerButton.getStyleClass().add("button-click");
		registerButton.setOnAction(event -> register());
		registerButton.setMinWidth(160);
		registerButton.setMinHeight(35);
		GridPane.setHalignment(registerButton, HPos.RIGHT);
		grid.add(registerButton, 1, 4);
		
		Image image = new Image(getClass().getResourceAsStream("close2.png"));
        Button button1 = new Button();
        button1.getStyleClass().add("close-button");
        button1.setGraphic(new ImageView(image));
        button1.setOnAction(new EventHandler<ActionEvent>() {
           @Override public void handle(ActionEvent e) {
        	   Stage stage = (Stage) button1.getScene().getWindow();
        	    stage.close();
            }
        });

		notification = new Text();
		grid.add(notification,1,6);

		AnchorPane anchorPane = new AnchorPane();
		AnchorPane.setBottomAnchor(grid, 0.0);
		AnchorPane.setLeftAnchor(grid, 0.0);
		AnchorPane.setRightAnchor(grid, 0.0);
		AnchorPane.setTopAnchor(grid, 80.0);
		AnchorPane.setRightAnchor(button1, 0.0);
		AnchorPane.setTopAnchor(button1, 0.0);
		anchorPane.getChildren().addAll(button1, grid);

		Scene scene = new Scene(anchorPane, 700, 800);
		scene.getStylesheets() .add(getClass().getResource("application.css").toExternalForm());

		// Pressing Enter works as pressing button "Sign in"
		grid.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode() == KeyCode.ENTER) {
				this.logIn();
			}
		});

		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

//	private Uzytkownicy findUserByNameOrEmail(String nameOrEmail) {
//		Register register = this.mainApp.getRegister();
//
//		for (Uzytkownicy user : register.lista) {
//			if (user.firstName.equals(nameOrEmail)) {
//				return user;
//			}
//
//			if (user.mail.equals(nameOrEmail)) {
//				return user;
//			}
//		}
//
//		// returns null if no matching firstName or email is found
//		return null;
//	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void hide() {
		if (this.primaryStage != null) {
			this.primaryStage.hide();
		}
	}
}
