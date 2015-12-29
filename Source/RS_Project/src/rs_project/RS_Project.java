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
import com.sun.deploy.trace.Trace;
import com.sun.javafx.geom.Vec2f;
import com.sun.javaws.Main;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author Егор
 */
    public class RS_Project extends Application {
    private ArrayList<Subject> subjectList = new ArrayList<>();
    Timer timer;
    static double firstSubjectPosition = 100;
    //ImageView imgNext;
    GridPane grid;
    DaysOfWeek CurrentDay = DaysOfWeek.MONDAY;
    ArrayList<Subject> CurrentSbj;
    ArrayList<EventImage> imgEvent;
    
    //public static boolean isClosed;
    
    frmAddSubjectEvent fm = new frmAddSubjectEvent();
    Stage pm;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("RS Project");
        EventTimer.SetParent(this);
        pm = primaryStage;
        subjectList.clear();
        ParseXML.scanXml(subjectList);
        CurrentSbj = LoadSubjects(subjectList, CurrentDay);
        ArrayList<Label> sbjName = WriteSubjectsToLabels(CurrentSbj);
        imgEvent = AddEventsSumbol(CurrentSbj);
                
        fm.SetParent(this);

        
        //Pane root = new Pane();


        //-----------------***Инициализация компонентов формы***----------------
        //root.getChildren().add(btn);
        //root.getChildren().addAll(sbjName);
        
        Button btnNext = new Button();
        btnNext.setText("Next Day");
        
        Button btnPrevious = new Button();
        btnPrevious.setText("Previous Day");
        
        Label lblToday = new Label(CurrentDay.toString());
        
        
        //ImageView imgEvent;


        grid = CreateGrid(sbjName);
        grid.add(lblToday,3,0);
        grid.add(btnNext,6, 7);
        grid.add(btnPrevious, 2, 7);
        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //-----------***СОБЫТИЯ***-----------
                btnNext.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                grid.getChildren().clear();
                CurrentDay = GetNextDay(CurrentDay);
                start(primaryStage);
            }
        });
                
               btnPrevious.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                grid.getChildren().clear();
                
                CurrentDay = GetPreviousDay(CurrentDay);
                start(primaryStage);
              
            }
        });
               
        EventTimer.StartTimersOnProgramLoad(subjectList);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private static ArrayList<Subject> LoadSubjects(ArrayList<Subject> Subjects, DaysOfWeek day) {
       ArrayList<Subject> CurrentSubjects = new ArrayList<Subject>();
       for (Subject sbj : Subjects) {
           if (sbj.getDay()== day){
               CurrentSubjects.add(sbj);
           }
        }
       return CurrentSubjects;
    }
    private  ArrayList<Label> WriteSubjectsToLabels (ArrayList<Subject> Subjects){
        ArrayList <Label> labelList = new ArrayList<>();
        //double counter = firstSubjectPosition;
        for (int i = 0;i<Subjects.size();i++){
            
            Label tempLabel = new Label(this.GetLabelName(Subjects.get(i)));
            //tempLabel.setLayoutX(counter);
            //tempLabel.setLayoutY(200);
            tempLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
            labelList.add(tempLabel);
            addTranslateListener(tempLabel);
            //counter++;
        }
        return labelList;
    }
    
    public  void Refresh()
    {
        start(this.pm);
    }
    
    private ArrayList<EventImage> AddEventsSumbol(ArrayList<Subject> Subjects){
        ArrayList<EventImage> imgList = new ArrayList<>();
        for(int i = 0; i<Subjects.size();i++){
            if (!Subjects.get(i).getEventList().isEmpty()){
                ImageView imgEvent = new ImageView("images/event.png");
                imgEvent.setFitHeight(20);
                imgEvent.setFitWidth(20);
                
                EventImage userIME=new EventImage();
                userIME.SetImg(imgEvent);
                userIME.SetNumber(i);
                userIME.SetEvents((ArrayList<SubjectEvent>) Subjects.get(i).getEventList());
                
                imgList.add(userIME);
            }
        }
        return imgList;
    }
    
    private String GetLabelName(Subject sbj){
            String number = String.valueOf(sbj.getNumberInTable());
            number +=". ";
            String name = sbj.getSubjectName();
            name +="\n";
            String lect = sbj.getLecturerName();
            String result = number+name+lect;
            return result;
    }
    
    private GridPane CreateGrid (ArrayList<Label> sbjName){
        GridPane grid = new GridPane();
        int count = 0;
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        for (int i = 0; i<sbjName.size();i++){
        grid.add(sbjName.get(i), 3, i+1);

        if (count<imgEvent.size())
        {
        if (imgEvent.get(count).GetNumber()==i){
            grid.add(imgEvent.get(count).GetImg(), 4,i+1);
            addImageClick(imgEvent.get(i));
            count++;
        }
        }
        
        }
        return grid;
    }
    

            
    
    private DaysOfWeek GetNextDay(DaysOfWeek cDay){
        if (cDay==DaysOfWeek.MONDAY){
            return DaysOfWeek.TUESDAY;
        }
        
        if (cDay==DaysOfWeek.TUESDAY){
            return DaysOfWeek.WEDNESDAY;
        }
        
        
        if (cDay==DaysOfWeek.WEDNESDAY){
            return DaysOfWeek.THURSDAY;
        }
        
       
        if (cDay==DaysOfWeek.THURSDAY){
            return DaysOfWeek.FRIDAY;
        }
        
        
        if (cDay==DaysOfWeek.FRIDAY){
            return DaysOfWeek.SATURDAY;
        }
        
        if (cDay==DaysOfWeek.SATURDAY){
            return DaysOfWeek.MONDAY;
        }
        return null;
    }
    
        private DaysOfWeek GetPreviousDay(DaysOfWeek cDay){
        if (cDay==DaysOfWeek.TUESDAY){
            return DaysOfWeek.MONDAY;
        }
        
        if (cDay==DaysOfWeek.WEDNESDAY){
            return DaysOfWeek.TUESDAY;
        }
        
        
        if (cDay==DaysOfWeek.THURSDAY){
            return DaysOfWeek.WEDNESDAY;
        }
        
       
        if (cDay==DaysOfWeek.FRIDAY){
            return DaysOfWeek.THURSDAY;
        }
        
        
        if (cDay==DaysOfWeek.SATURDAY){
            return DaysOfWeek.FRIDAY;
        }
        
        if (cDay==DaysOfWeek.MONDAY){
            return DaysOfWeek.SATURDAY;
        }
        return null;
    }
      
        private Subject GetSubjectByNumber(int number){
            for (Subject sbj: CurrentSbj){
                if (sbj.getNumberInTable()==number){
                    return sbj;
                }
                    
            }
            return null;
        }
        
        private int ParseLabel(Label lbl){
        String sbjInfo = lbl.getText();
        String substring = sbjInfo.substring(0, 1);
        int number = Integer.parseInt(substring);
        return number;
        }
        
        
        
        
    //Что-то странное    

   private void addTranslateListener(final Label node) { 
   node.setOnMousePressed(new EventHandler() { 


       @Override
       public void handle(Event event) {
           //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        String sbjInfo = node.getText();
//        String substring = sbjInfo.substring(0, 1);
//        int number = Integer.parseInt(substring);
        int number = ParseLabel(node);
        //frmAddSubjectEvent fm = new frmAddSubjectEvent();
        fm.SetSubject(GetSubjectByNumber(number));
        //fm.SetParent(this);
        //fm.SetSubject(null);
       try {
           fm.start(new Stage());
       } catch (Exception ex) {
           Logger.getLogger(RS_Project.class.getName()).log(Level.SEVERE, null, ex);
       }
       }
       
       
    });
   
   node.setOnMouseMoved(new EventHandler(){
   @Override
       public void handle(Event event) {
       node.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
   }
       
   });
   
     node.setOnMouseExited(new EventHandler(){
   @Override
       public void handle(Event event) {
       node.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
   }
       
   });
   
   }
   
      private void addImageClick(final EventImage node) { 
   node.GetImg().setOnMousePressed(new EventHandler() { 


       @Override
       public void handle(Event event) {
           frmEventInfo fm = new frmEventInfo();
           fm.SetEvents((ArrayList<SubjectEvent>) node.GetEvents());
           try {
               fm.start(new Stage());
           } catch (Exception ex) {
               Logger.getLogger(RS_Project.class.getName()).log(Level.SEVERE, null, ex);
           }

       }
       
       
    });
   }
   
}
    

  
    

    
    
