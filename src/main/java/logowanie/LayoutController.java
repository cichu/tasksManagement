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
            showCalendarWeekView();
            //showCalendarMonthView();
        }));
    }

    void showCalendarWeekView() {
        Stage stage = new Stage();
        stage.setTitle("Kalendarz");

        CalendarView calendarView = new CalendarView();

        calendarView.setTimeSlotLength(60);
        calendarView.start(stage);
    }

    void showCalendarMonthView() {
        Stage stage = new Stage();
        stage.setTitle("Kalendarz");

        CalendarMonthView calendarView = new CalendarMonthView();

        calendarView.start(stage);
    }
}
