/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.entities;



/**
 *
 * @author SBS
 */
import java.time.LocalDate;

public class CalendarActivity {
    
    private String employeName;
    private String activity;
    private LocalDate endDate;

    public CalendarActivity(String employeName, String activity, LocalDate endDate) {
        this.employeName = employeName;
        this.activity = activity;
        this.endDate = endDate;
    }

    public CalendarActivity() {
    }

    public CalendarActivity(String employeName) {
        this.employeName = employeName;
    }
    

    public String getEmployeName() {
        return employeName;
    }

    public void setEmployeName(String employeName) {
        this.employeName = employeName;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "CalendarActivity{" + "employeName=" + employeName + ", activity=" + activity + ", endDate=" + endDate + '}';
    }
    
}
