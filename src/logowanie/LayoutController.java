package logowanie;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LayoutController {

    @FXML
    private Button calendarButton;

    @FXML
    void initialize() {
        this.calendarButton.setOnMouseClicked((event -> {
            CalendarView calendarView = new CalendarView();
            calendarView.start(new Stage());
        }));
    }
}
