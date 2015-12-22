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
public class Subject {
    private String subjectName;
    private String lecturerName;
    private int audience;
    private int housing;
    private List<SubjectEvent> eventList;
    private int numberInTable;
    private DaysOfWeek day;
    
    public Subject (){
        this.eventList = new ArrayList<>();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public int getAudience() {
        return audience;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }

    public int getHousing() {
        return housing;
    }

    public void setHousing(int housing) {
        this.housing = housing;
    }

    public List<SubjectEvent> getEventList() {
        return eventList;
    }

    public void setEventList(List<SubjectEvent> eventList) {
        this.eventList = eventList;
    }

    public int getNumberInTable() {
        return numberInTable;
    }

    public void setNumberInTable(int numberInTable) {
        this.numberInTable = numberInTable;
    }

    public DaysOfWeek getDay() {
        return day;
    }

    public void setDay(DaysOfWeek day) {
        this.day = day;
    }
}
