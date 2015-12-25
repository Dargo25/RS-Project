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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    private static ArrayList<Subject> subjectList = new ArrayList<>();
    Timer timer;
    static double firstSubjectPosition = 100;
    ImageView imgNext;
    GridPane grid;
    DaysOfWeek CurrentDay = DaysOfWeek.MONDAY;
    
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("RS Project");

        subjectList.clear();
        ParseXML.scanXml(subjectList);
        ArrayList<Subject> CurrentSbj = LoadSubjects(subjectList, CurrentDay);
        ArrayList<Label> sbjName = WriteSubjectsToLabels(CurrentSbj);
                


        
        //Pane root = new Pane();


        //-----------------***Инициализация компонентов формы***----------------
        //root.getChildren().add(btn);
        //root.getChildren().addAll(sbjName);
        
        Button btnNext = new Button();
        btnNext.setText("Next Day");
        
        Button btnPrevious = new Button();
        btnPrevious.setText("Previous Day");
        
        Label lblToday = new Label(CurrentDay.toString());
        //imgNext = new ImageView(new Image(RS_Project.class.getResourceAsStream("images/btnNext.png")));
        //JLabel imgNex = new JLabel(new ImageIcon("images/btnNext.png"));


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
                frmAddSubjectEvent fm = new frmAddSubjectEvent();
                try {
                    fm.start(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(RS_Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
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
    private static ArrayList<Label> WriteSubjectsToLabels (ArrayList<Subject> Subjects){
        ArrayList <Label> labelList = new ArrayList<>();
        double counter = firstSubjectPosition;
        for (int i = 0;i<Subjects.size();i++){
            Label tempLabel = new Label(Subjects.get(i).getSubjectName());
            tempLabel.setLayoutX(counter);
            tempLabel.setLayoutY(200);
            labelList.add(tempLabel);
            counter++;
        }
        return labelList;
    }
    private GridPane CreateGrid (ArrayList<Label> sbjName){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        for (int i = 0; i<sbjName.size();i++){
        grid.add(sbjName.get(i), 3, i+1);
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
    
}    
    
    
