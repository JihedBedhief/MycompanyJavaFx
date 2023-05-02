package gestionrh.gui;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import gestionrh.entities.Contrat;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class CalendrierController implements Initializable {


    private GridPane gridPane;

    private YearMonth currentYearMonth;
    @FXML
    private BorderPane root;
    @FXML
    private HBox dayHeader;
    @FXML
    private Label monthYearLabel;
    @FXML
    private GridPane calendarPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentYearMonth = YearMonth.now();
        updateCalendar(currentYearMonth);
    }

    public void updateCalendar(YearMonth yearMonth) {
        gridPane.getChildren().clear();

        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM");
        String monthName = yearMonth.format(monthFormatter);
        Label monthLabel = new Label(monthName);
        monthLabel.setStyle("-fx-font-size: 18pt; -fx-font-weight: bold;");
        gridPane.add(monthLabel, 0, 0, 7, 1);

        LocalDate firstOfMonth = yearMonth.atDay(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 0; i < 7; i++) {
            Label dayLabel = new Label(firstOfMonth.plusDays(i).getDayOfWeek().toString());
            dayLabel.setStyle("-fx-font-size: 12pt; -fx-font-weight: bold;");
            gridPane.add(dayLabel, i, 1);
        }

        int row = 2;
        int col = dayOfWeek - 1;

        for (int day = 1; day <= yearMonth.lengthOfMonth(); day++) {
            StackPane cell = new StackPane();
            Label label = new Label(Integer.toString(day));
            label.setStyle("-fx-font-size: 10pt;");
            cell.getChildren().add(label);
            gridPane.add(cell, col, row);
            col++;

            if (col > 6) {
                col = 0;
                row++;
            }
        }
    }

    public void setContratEndDates(List<Contrat> contrats) {
        for (Contrat contrat : contrats) {
            LocalDate debut = contrat.getDatedebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fin = contrat.getDatefin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (debut != null && fin != null) {
                int row = debut.getDayOfMonth() + 1;
                int col = debut.getMonthValue() - 1;
                while (debut.isBefore(fin) || debut.equals(fin)) {
                    StackPane cell = (StackPane) gridPane.getChildren().get(row * 7 + col);
                    Label label = new Label(debut.getDayOfMonth() + "\n" + contrat.getEmp().getNom());
                    label.setStyle("-fx-font-size: 10pt;");
                    cell.getChildren().add(label);
                    debut = debut.plusDays(1);
                }
            }
        }
    }

    
}
