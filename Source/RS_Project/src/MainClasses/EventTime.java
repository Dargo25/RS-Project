/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Егор
 */
public class EventTime {
    private Date date;
    private Time singleEventTime;
    private Time startTime;
    private Time endTime;
    private EventType type;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getSingleEventTime() {
        return singleEventTime;
    }

    public void setSingleEventTime(Time singleEventTime) {
        this.singleEventTime = singleEventTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }
}
