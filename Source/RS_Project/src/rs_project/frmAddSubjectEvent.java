/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rs_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import MainClasses.*;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;


/**
 *
 * @author Samosad
 */
public class frmAddSubjectEvent extends Application{
    Button btnAddEvent;
    SubjectEvent userEvent = new SubjectEvent();
    TextField txtEventName;
    TextField txtEventContent;
    DatePicker dpEventTime;
    Subject userSubject;
    TextField txtHour;
    TextField txtMinutes;
    
    static Stage ps;
    RS_Project rsProj;
    
    Label lbl = new Label();
    
    
    SubjectEvent testEv;
    
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        ps = primaryStage;
        //userSubject = new Subject();
        
        primaryStage.setTitle("Добавить событие");
        GridPane root = CreateGrid();
        root.add(lbl,0,7);
        Scene scn = new Scene(root,350,200);
        primaryStage.setScene(scn);
        primaryStage.show();
        
        btnAddEvent.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
         userEvent = new SubjectEvent();
         userEvent.setHeader(txtEventName.getText());
         userEvent.setContent(txtEventContent.getText());
         userEvent.setType(EventType.SINGLE);
         
        LocalDate localDate = dpEventTime.getValue();
        Instant instant;
        
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        int hour = Integer.parseInt(txtHour.getText());
        int minutes = Integer.parseInt(txtMinutes.getText());
        EventTime userTime = new EventTime();
        userTime.setDate(new Date(year-1900, month-1, day, hour, minutes));
        //userEvent.getTimeList().add(userTime);
        
//        ArrayList<EventTime> listEV = new ArrayList<EventTime>();
//        listEV.add(userTime);
//        userEvent.setTimeList(listEV);
        userEvent.setTime(userTime);
        
        userSubject.getEventList().add(userEvent);
            WriteEventToXML(userSubject);
        //ПОКА РАБОТАЕМ С ТЕСТОВЫМ ПРЕДМЕТОМ
//            try {
//                ParseXML.AddNewEventToXML(userSubject);
//                //Date date = Date.from(instant);
//            } catch (SAXException ex) {
//                Logger.getLogger(frmAddSubjectEvent.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(frmAddSubjectEvent.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ParserConfigurationException ex) {
//                Logger.getLogger(frmAddSubjectEvent.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (TransformerException ex) {
//                Logger.getLogger(frmAddSubjectEvent.class.getName()).log(Level.SEVERE, null, ex);
//            }
//         
            CreateTimer(userEvent);
            
         //userTime.setDate(date);
            
        String a = String.valueOf(day);
         lbl = new Label("Задание добавленно");
         rsProj.Refresh();
         
            try {
                start(primaryStage);
                
                
            } catch (Exception ex) {
                Logger.getLogger(frmAddSubjectEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
         
            }
        });
//        GridPane root = CreateGrid();
//        //root.add(lbl,0,5);
//        Scene scn = new Scene(root,350,150);
//        primaryStage.setScene(scn);
//        primaryStage.show();   
    }
    
    private void WriteEventToXML(Subject userSubject){
                    try {
                ParseXML.AddNewEventToXML(userSubject);
                //Date date = Date.from(instant);
            } catch (SAXException ex) {
                Logger.getLogger(frmAddSubjectEvent.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(frmAddSubjectEvent.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(frmAddSubjectEvent.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(frmAddSubjectEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    private void CreateTimer(SubjectEvent event) {
        Timer timer = new Timer();

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
    
        
//        timer.schedule(task, event.getTime().getDate());
    }
    
    private GridPane CreateGrid() throws ParseException{
        GridPane grid = new GridPane();
        Label lblAddEvent = new Label("Введите информацию о событии");
        lblAddEvent.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        Label lblEventName = new Label("Введите заголовок события");
        txtEventName = new TextField();
        
        Label lblEventContent = new Label("Введите описание события");
        txtEventContent = new TextField();
        
        dpEventTime = new DatePicker();
        Label lblEventTime = new Label("Выберете дату события");
        
        txtHour = new TextField();
        Label lblHours = new Label("Часы");
        
        txtMinutes = new TextField();
        Label lblMinutes = new Label("Минуты");
        
        grid.add(lblAddEvent, 0, 0, 2, 1);
        
        grid.add(lblEventName,0,1);
        grid.add(txtEventName,1,1);
        
        grid.add(lblEventContent, 0,2);
        grid.add(txtEventContent, 1,2);
        
        grid.add(dpEventTime, 1, 3);
        grid.add(lblEventTime, 0, 3);
        
        grid.add(lblHours,0,4);
        grid.add(txtHour,1,4);
        
        grid.add(lblMinutes, 0, 5);
        grid.add(txtMinutes, 1, 5);
        
        
        btnAddEvent = new Button("Добавить");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btnAddEvent);
        grid.add(hbBtn, 1, 7);
        
        return grid;
    }
    public void SetSubject(Subject sbj){
        userSubject = sbj;
    }
    private void WatchEvent(SubjectEvent event){
                frmEventMessage fm = new frmEventMessage();
                fm.SetEventName(event.getHeader());
                fm.SetEventContent(event.getContent());
                try {
                    fm.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(frmAddSubjectEvent.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    public void SetParent (RS_Project prj){
        rsProj = prj;
    }
    
    

    
    
    

    
}
