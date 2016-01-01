/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import rs_project.frmAddSubjectEvent;
import rs_project.frmEventMessage;
import rs_project.RS_Project;

/**
 *
 * @author Егор
 */
public final class EventTimer {
    static RS_Project parent;
    private static ArrayList<Timer> timerList = new ArrayList<>();
    
    public static void CreateTimer(Subject subject, SubjectEvent event) {
        Timer timer = new Timer();
        
        timer.schedule(new TimerTask() {
            
            @Override
            public void run() {
                    Platform.runLater(() -> {
                        try {
                            WorkWithXML.RemoveEventFromXml(subject, event);
                        } catch (SAXException ex) {
                            Logger.getLogger(EventTimer.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(EventTimer.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParserConfigurationException ex) {
                            Logger.getLogger(EventTimer.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (TransformerException ex) {
                            Logger.getLogger(EventTimer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        parent.Refresh();
                        WatchEvent(event);
                });      
            }
        }, event.getTime().getDate());
        getTimerList().add(timer);
    }
    
    private static void WatchEvent(SubjectEvent event){
        frmEventMessage message = new frmEventMessage();
        message.SetEventName(event.getHeader());
        message.SetEventContent(event.getContent());
        try {
            message.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(frmAddSubjectEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void StartTimersOnProgramLoad(ArrayList<Subject> subjectList) {
        for (Subject subject : subjectList) {
            for (SubjectEvent event : subject.getEventList()) {
                CreateTimer(subject, event);
            }
        }
    }
    
    public static void SetParent(RS_Project prn){
        parent = prn;
    }

    /**
     * @return the timerList
     */
    public static ArrayList<Timer> getTimerList() {
        return timerList;
    }
}
