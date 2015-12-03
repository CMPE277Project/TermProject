package edu.sjsu.cmpe277.termproject.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by emy on 11/22/15.
 */
public class Event {

    private String title;
    private String description;
    private String startTime;
    private String endTime;
    private User user;
    private String eventId;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        //simpleDateFormat.format(date);
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
//        this.endTime = simpleDateFormat.format(date);
        this.endTime = endTime;
    }
}
