/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

/**
 *
 * @author Егор
 */
public class EventTime {
    private Date date;
    private Time singleEventTime;
    private Time startTime;
    private Time endTime;
    private int interval; //maybe i'll call it period later...
    private Timer timer;

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
}
