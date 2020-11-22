package ro.siit.airports.model;

import java.time.LocalDateTime;

public class Dates {

    private String startDateString = LocalDateTime.now().toString();
    private String endDateString = LocalDateTime.now().toString();


    public String getStartDateString() {
        return startDateString;
    }

    public void setStartDateString(String startDateString) {
        this.startDateString = startDateString;
    }

    public String getEndDateString() {
        return endDateString;
    }

    public void setEndDateString(String endDateString) {
        this.endDateString = endDateString;
    }

}
