/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.stage.Stage;
import rs_project.frmAddSubjectEvent;
import rs_project.frmEventMessage;

/**
 *
 * @author Егор
 */
public final class EventTimer {
    
    private static void CreateTimer(SubjectEvent event) {
        Timer timer = new Timer();
        
        //TimerTask task;
        timer.schedule(new TimerTask() {
            
            @Override
            public void run() {
                Platform.runLater(() -> {
                //System.out.println(event.getHeader() + "\n\t" + event.getContent());
                //форма, при срабатывании таймера
                WatchEvent(event);
                //timer.cancel();
                //Scene sc = new Scene(lbl);
                //Stage ps = new Stage();
                //ps.setScene(sc);
                //ps.show();
                });

            }
//        }, event.getTimeList().get(event.getTimeList().size() - 1).getDate());
        }, event.getTime().getDate());
    
        
        //timer.schedule(task, event.getTimeList().get(event.getTimeList().size() - 1).getDate());
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
                CreateTimer(event);
            }
        }
    }
}
