/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Егор
 */
public class SubjectEvent {    
    private EventType type;
    private String header;
    private String content;
    private List<EventTime> timeList;
    
    public SubjectEvent(){
        this.timeList = new ArrayList<>();
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<EventTime> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<EventTime> timeList) {
        this.timeList = timeList;
    }
}
