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
import javafx.scene.layout.Priority;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
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

    // First day of current month
    private final LocalDate firstSlotStart = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),1);
    // One time slot = one day
    // Period ==> days, weeks, months, years
    // Duration ==> seconds, minutes, days
    private final Period slotLength = Period.ofDays(1);
    //private final Duration slotLength = Duration.ofDays(1);
    //private final LocalDate lastSlotStart = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().lengthOfMonth() - 1);

    private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

    private final List<TimeSlot> timeSlots = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        GridPane calendarView = new GridPane();

        ObjectProperty<TimeSlot> mouseAnchor = new SimpleObjectProperty<>();

        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.minusDays(today.getDayOfMonth() - 1);
        LocalDate endOfMonth = startOfMonth.plusDays(today.getMonth().maxLength() - 1);
        
        LocalDate startOfWeek = startOfMonth.minusDays(startOfMonth.getDayOfWeek().getValue() - 1); 

        int slotIndex = 1;
        
        // przechodzenie po tygodniach 
        for (LocalDate date = startOfWeek; !date.isAfter(endOfMonth); /*dateMonth = dateMonth.plusDays(7)*/) {
            
            LocalDate endOfWeek = date.plusDays(6);
            for (/*LocalDate date = date*/ ; !date.isAfter(endOfWeek); date = date.plusDays(1)) {
            	TimeSlot timeSlot = new TimeSlot(date, slotLength);
            	timeSlots.add(timeSlot);
            	
            	registerDragHandlers(timeSlot, mouseAnchor);
            	
            	calendarView.add(timeSlot.getView(), timeSlot.getDayOfWeek().getValue(), slotIndex);
            }
            
            slotIndex++;
        }

        // formatowanie nazw dni na górze tabeli
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("E");
        for (LocalDate date = startOfMonth; ! date.isAfter(endOfMonth); date = date.plusDays(1)) {
            Label label = new Label(date.format(dayFormatter));
            label.setPadding(new Insets(1));
            //label.setTextAlignment(TextAlignment.CENTER);
            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(label);
            stackPane.getStyleClass().add("day-header");
            GridPane.setHalignment(stackPane, HPos.CENTER);
            calendarView.add(stackPane, date.getDayOfWeek().getValue(), 0);
        }

        // formatowanie numerów tygodni po lewej stronie tabeli
        slotIndex = 1 ;
        //DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("w");
        for (LocalDate day = startOfMonth; !day.isAfter(endOfMonth); day = day.plusDays(7)) {
            int weekNumber = day.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
            Label label = new Label(Integer.toString(weekNumber));
            label.setPadding(new Insets(2));
            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(label);
            stackPane.getStyleClass().add("week-header");
            GridPane.setHalignment(stackPane, HPos.RIGHT);
            calendarView.add(stackPane, 0, slotIndex);
            slotIndex++ ;
        }

        ColumnConstraints  c1 = new ColumnConstraints();
        c1.setHgrow(Priority.NEVER);
        ColumnConstraints  c2 = new ColumnConstraints();
        c2.setHgrow(Priority.ALWAYS);
        
        RowConstraints r1 = new RowConstraints();
        r1.setVgrow(Priority.NEVER);
        RowConstraints r2 = new RowConstraints();
        r2.setVgrow(Priority.ALWAYS);
        
        calendarView.getColumnConstraints().addAll(c1, c2, c2, c2, c2, c2, c2, c2);
        calendarView.getRowConstraints().addAll(r1, r2, r2, r2, r2, r2);

        //ScrollPane scroller = new ScrollPane(calendarView);
        AnchorPane ap = new AnchorPane(calendarView);
        
        AnchorPane.setBottomAnchor(calendarView, 0.0);
        AnchorPane.setTopAnchor(calendarView, 0.0);
        AnchorPane.setLeftAnchor(calendarView, 0.0);
        AnchorPane.setRightAnchor(calendarView, 0.0);

        Scene scene = new Scene(ap);
        scene.getStylesheets().add(getClass().getResource("calendar-view.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

        // set after showing primaryStage
        // so that ap width and height are already set
        primaryStage.setMinWidth(ap.getWidth() + 20);
        primaryStage.setMinHeight(ap.getHeight() + 40);
    }

    private void registerDragHandlers(TimeSlot timeSlot, ObjectProperty<TimeSlot> mouseAnchor) {
        // TODO
        timeSlot.getView().setOnMouseClicked( event -> {
            //mouseAnchor.set(timeSlot);
            //timeSlot.getView().startFullDrag();
            timeSlots.forEach(slot ->
                slot.setSelected(slot == timeSlot));
        });
        /*
        timeSlot.getView().setOnMouseDragEntered( event -> {
            TimeSlot startSlot = mouseAnchor.get();
            //timaSlots.forEach(isBetween());
        });
         */
        timeSlot.getView().setOnMouseReleased( event -> mouseAnchor.set(null));
    }

    private class TimeSlot {
        private final LocalDate start;
        private final Period duration;
        private final Pane view;
        private final Label dayNumber;

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

            view = new Pane();
            view.setMinSize(80, 80);
            view.getStyleClass().add("time-slot");
            
            dayNumber = new Label();
            dayNumber.setText(Integer.toString(start.getDayOfMonth()));
            if (start.getMonth() == LocalDate.now().getMonth()) {
            	// days belong to currently view month
                dayNumber.getStyleClass().add("day-number");
            } else {
            	// days from other months
            	dayNumber.getStyleClass().add("day-number-other");
            }
            dayNumber.setPadding(new Insets(1));
            
            view.getChildren().add(dayNumber);

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
    
    public static void main(String args[]) {
    	launch(args);
    }
}