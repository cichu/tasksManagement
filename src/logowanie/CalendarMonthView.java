/* package logowanie;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.PseudoClass;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarMonthView extends Application {

    private final LocalDate firstSlotStart = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),0);
    private final Duration slotLength = Duration.ofDays(1);
    private final LocalDate lastSlotStart = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().lengthOfMonth() - 1);

    // private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

    private final List<CalendarView.TimeSlot> timaSlots = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        GridPane calendarView = new GridPane();

        ObjectProperty<CalendarView.TimeSlot> mouseAnchor = new SimpleObjectProperty<>();

        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.minusDays(today.getDayOfMonth() - 1);
        LocalDate endOfMonth = startOfMonth.plusDays(today.getMonth().maxLength());

        for (LocalDate date = startOfMonth; ! date.isAfter(endOfMonth); date = date.plusDays(1)) {
            int slotIndex = 1;

            // czy musi być
            for (LocalDate startDay = date.;
                 ! startDay.isAfter(date.atTime(lastSlotStart));
                 startDay = startDay.plus(slotLength);)
        }
    }
}
*/