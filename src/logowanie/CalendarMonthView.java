package logowanie;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.PseudoClass;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CalendarMonthView extends Application {

    private final LocalDate firstSlotStart = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),1);
    private final Period slotLength = Period.ofDays(1);
    //private final Duration slotLength = Duration.ofDays(1);
    private final LocalDate lastSlotStart = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().lengthOfMonth() - 1);

    private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

    private final List<TimeSlot> timaSlots = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        GridPane calendarView = new GridPane();

        ObjectProperty<TimeSlot> mouseAnchor = new SimpleObjectProperty<>();

        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.minusDays(today.getDayOfMonth() - 1);
        LocalDate endOfMonth = startOfMonth.plusDays(today.getMonth().maxLength());

        for (LocalDate date = startOfMonth; ! date.isAfter(endOfMonth); date = date.plusDays(1)) {
            int slotIndex = 1;

            for (LocalDate startDay = date;
                 ! startDay.isAfter(lastSlotStart);
                 startDay = startDay.plus(slotLength)) {

                TimeSlot timeSlot = new TimeSlot(startDay, slotLength);
                timaSlots.add(timeSlot);

                registerDragHandlers(timeSlot, mouseAnchor);

                // node, kolumna, rząd
                calendarView.add(timeSlot.getView(), timeSlot.getDayOfWeek().getValue(), (int) Math.ceil(slotIndex/7));

                slotIndex++;
            }
        }

        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("E");

        for (LocalDate date = startOfMonth; ! date.isAfter(endOfMonth); date = date.plusDays(1)) {
            Label label = new Label(date.format(dayFormatter));
            label.setPadding(new Insets(1));
            label.setTextAlignment(TextAlignment.CENTER);
            GridPane.setHalignment(label, HPos.CENTER);
            calendarView.add(label, date.getDayOfWeek().getValue(), 0);
        }

        // formatowanie numerów tygodni
        int slotIndex = 1 ;
        //DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("w");
        for (LocalDate day = startOfMonth; !day.isAfter(endOfMonth); day = day.plusDays(7)) {
            int weekNumber = day.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
            Label label = new Label(Integer.toString(weekNumber));
            label.setPadding(new Insets(2));
            GridPane.setHalignment(label, HPos.RIGHT);
            calendarView.add(label, 0, slotIndex);
            slotIndex++ ;
        }

        ScrollPane scroller = new ScrollPane(calendarView);

        Scene scene = new Scene(scroller);
        scene.getStylesheets().add(getClass().getResource("calendar-view.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void registerDragHandlers(TimeSlot timeSlot, ObjectProperty<TimeSlot> mouseAnchor) {
        // TODO
        timeSlot.getView().setOnDragDetected( event -> {
            mouseAnchor.set(timeSlot);
            timeSlot.getView().startFullDrag();
            timaSlots.forEach(slot ->
                slot.setSelected(slot == timeSlot));
        });

        timeSlot.getView().setOnMouseDragEntered( event -> {
            TimeSlot startSlot = mouseAnchor.get();
            //timaSlots.forEach(isBetween());
        });

        timeSlot.getView().setOnMouseReleased( event -> mouseAnchor.set(null));
    }

    private class TimeSlot {
        private final LocalDate start;
        private final Period duration;
        private final Region view;

        private final BooleanProperty selected = new SimpleBooleanProperty();

        public final BooleanProperty selectedProperty() {
            return this.selected;
        }

        public final boolean isSelected() {
            return this.selected.get();
        }

        public final void setSelected(boolean selected) {
            this.selectedProperty().set(selected);
        }

        public TimeSlot(LocalDate start, Period duration) {
            this.start = start;
            this.duration = duration;

            view = new Region();
            view.setMinSize(80, 80);
            view.getStyleClass().add("time-slot");

            selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                view.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, isSelected);
            });
        }

        public LocalDate getStart() {
            return start;
        }

        public Node getView() {
            return this.view;
        }

        public DayOfWeek getDayOfWeek() {
            return this.start.getDayOfWeek();
        }
    }
}