/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import gestionrh.entities.CalendarActivity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

import java.time.ZonedDateTime;
import java.util.*;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;



public class CalendarController implements Initializable {

    ZonedDateTime dateFocus;
    ZonedDateTime today;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
       }

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }
   
    private void drawCalendar(){
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        //List of activities for a given month
        Map<Integer, List<CalendarActivity>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth =(calendarWidth/7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight/6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j+1)+(7*i);
                if(calculatedDate > dateOffset){
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
                        if(calendarActivities != null){
                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void createCalendarActivity(List<CalendarActivity> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();
for (int k = 0; k < calendarActivities.size(); k++) {
    // Check if startDate is not null
    if (calendarActivities.get(k).getStartDate() == null) {
        continue;
    }
    if(k >= 2) {
        Text moreActivities = new Text("...");
        calendarActivityBox.getChildren().add(moreActivities);
        moreActivities.setOnMouseClicked(mouseEvent -> {
            //On ... click print all activities for given date
            System.out.println(calendarActivities);
        });
        break;
    }
    Text text = new Text(calendarActivities.get(k).getClientName() + ", " + calendarActivities.get(k).getStartDate().toLocalTime());
    calendarActivityBox.getChildren().add(text);
    text.setOnMouseClicked(mouseEvent -> {
        //On Text clicked
        System.out.println(text.getText());
    });
}

        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        calendarActivityBox.setStyle("-fx-background-color:GRAY");
        stackPane.getChildren().add(calendarActivityBox);
    }

   private Map<Integer, List<CalendarActivity>> createCalendarMap(List<CalendarActivity> calendarActivities) {
    Map<Integer, List<CalendarActivity>> calendarActivityMap = new HashMap<>();

    for (CalendarActivity activity: calendarActivities) {
        int activityDate = activity.getStartDate().getDayOfMonth();

        if(!calendarActivityMap.containsKey(activityDate)){
            calendarActivityMap.put(activityDate, new ArrayList<>(Arrays.asList(activity)));
        } else {
            List<CalendarActivity> oldListByDate = calendarActivityMap.get(activity.getStartDate().getDayOfMonth());


            List<CalendarActivity> newList = new ArrayList<>(oldListByDate);
            newList.add(activity);
            calendarActivityMap.put(activityDate, newList);
        }
    }
    return  calendarActivityMap;
}


private Map<Integer, List<CalendarActivity>> getCalendarActivitiesMonth(ZonedDateTime date) {
    Map<Integer, List<CalendarActivity>> calendarActivities = new HashMap<>();
    int year = date.getYear();
    int month = date.getMonthValue();
    Connection connection = null;

    try {
        // Establish a connection to the MySQL database
        String url = "jdbc:mysql://localhost:3306/my_company";
        String user = "root";
        String password = "";
        connection = DriverManager.getConnection(url, user, password);

        // Execute a query to retrieve the required data
        String query = "SELECT   c.datedebut, c.datefin, e.id, e.nom, e.prenom " +
                "FROM contrat c " +
                "JOIN employees e ON c.emp = e.id " +
                "WHERE YEAR(c.datedebut) = ? AND MONTH(c.datedebut) = ? OR YEAR(c.datefin) = ? AND MONTH(c.datefin) = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, year);
        statement.setInt(2, month);
        statement.setInt(3, year);
        statement.setInt(4, month);
        
        ResultSet resultSet = statement.executeQuery();

        // Process the result set and populate the calendar activities map
        while (resultSet.next()) {
            String employeName = resultSet.getString("nom") + " " + resultSet.getString("prenom");
            String dated= resultSet.getDate("datedebut") + " " + resultSet.getDate("datefin");
                      LocalDate startDate = resultSet.getDate("datedebut").toLocalDate();
                     LocalDate endDate = resultSet.getDate("datefin").toLocalDate();
           CalendarActivity calendarActivity = new CalendarActivity(employeName, ZonedDateTime.of(startDate, LocalTime.MIDNIGHT, date.getZone()), ZonedDateTime.of(endDate, LocalTime.MIDNIGHT, date.getZone()).plusDays(1).minusNanos(1));

   

            for (LocalDate dateLocal = startDate; !dateLocal.isAfter(endDate); dateLocal = dateLocal.plusDays(1)) {
                int day = dateLocal.getDayOfMonth();
                List<CalendarActivity> activities = calendarActivities.computeIfAbsent(day, k -> new ArrayList<>());
                activities.add(calendarActivity);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return calendarActivities;
}






}