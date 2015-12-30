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
import javafx.stage.Stage;
import MainClasses.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Егор
 */
public class RS_Project extends Application {

    private ArrayList<Subject> subjectList = new ArrayList<>();
    GridPane grid;
//    DaysOfWeek currentDay = DaysOfWeek.MONDAY;
    DaysOfWeek currentDay;
    ArrayList<Subject> currentSbj;
    ArrayList<EventImage> imgEvent;
    static boolean isFirstLaunch = true;

    frmAddSubjectEvent frmAddSbj = new frmAddSubjectEvent();
    Stage currentStage;

    Button btnPrevious;
    Button btnNext;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("RS Project");
        currentStage = primaryStage;
        subjectList.clear();
        subjectList = DataProcessor.ScanXML(subjectList);
        
        currentDay = DataProcessor.ChooseCurrentDay(isFirstLaunch, currentDay);
        
        currentSbj = LoadSubjects(subjectList, currentDay);
        ArrayList<Label> sbjName = WriteSubjectsToLabels(currentSbj);

        imgEvent = AddEventsSumbol(currentSbj);

        GetParent();

        grid = CreateGrid(sbjName);

        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();

        //-----------***СОБЫТИЯ***-----------
        btnNext.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                grid.getChildren().clear();
                currentDay = DataProcessor.GetNextDay(currentDay);
                start(primaryStage);
            }
        });

        btnPrevious.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                grid.getChildren().clear();

                currentDay = DataProcessor.GetPreviousDay(currentDay);
                start(primaryStage);

            }
        });
        if (isFirstLaunch) {
            EventTimer.StartTimersOnProgramLoad(subjectList);
            isFirstLaunch = false;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void GetParent() {
        frmAddSbj.SetParent(this);
        EventTimer.SetParent(this);
    }

    private static ArrayList<Subject> LoadSubjects(ArrayList<Subject> subjects, DaysOfWeek day) {
        ArrayList<Subject> CurrentSubjects = new ArrayList<>();
        for (Subject sbj : subjects) {
            if (sbj.getDay() == day) {
                CurrentSubjects.add(sbj);
            }
        }
        return CurrentSubjects;
    }

    private ArrayList<Label> WriteSubjectsToLabels(ArrayList<Subject> subjects) {
        ArrayList<Label> labelList = new ArrayList<>();
        for (Subject Subject : subjects) {
            Label tempLabel = new Label(this.GetLabelName(Subject));
            tempLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
            labelList.add(tempLabel);
            addTranslateListener(tempLabel);
        }
        return labelList;
    }

    public void Refresh() {
        start(this.currentStage);
    }

    private ArrayList<EventImage> AddEventsSumbol(ArrayList<Subject> subjects) {
        ArrayList<EventImage> imgList = new ArrayList<>();
        for (int i = 0; i < subjects.size(); i++) {
            if (!subjects.get(i).getEventList().isEmpty()) {
                ImageView imgEvent = new ImageView("images/event.png");
                imgEvent.setFitHeight(25);
                imgEvent.setFitWidth(25);

                imgEvent.setOnMouseEntered(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        imgEvent.setFitHeight(28);
                        imgEvent.setFitWidth(28);
                    }
                });

                imgEvent.setOnMouseExited(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        imgEvent.setFitHeight(25);
                        imgEvent.setFitWidth(25);
                    }
                });

                EventImage userIME = new EventImage();
                userIME.SetImg(imgEvent);
                userIME.SetNumber(i);
                userIME.SetEvents((ArrayList<SubjectEvent>) subjects.get(i).getEventList());

                imgList.add(userIME);
            }
        }
        return imgList;
    }

    private String GetLabelName(Subject sbj) {
        String number = String.valueOf(sbj.getNumberInTable());
        number += ". ";
        String name = sbj.getSubjectName();
        name += "\n";
        String lect = sbj.getLecturerName();
        String audience = String.valueOf(sbj.getAudience());
        if (sbj.getAudience() == 0) {
            audience = "Спортзал";
        }
        String housing = String.valueOf(sbj.getHousing());
        String result = number + name + lect + " " + audience + "/" + housing;
        return result;
    }

    private GridPane CreateGrid(ArrayList<Label> sbjName) {
        GridPane grid = new GridPane();
        int count = 0;
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        for (int i = 0; i < sbjName.size(); i++) {
            grid.add(sbjName.get(i), 3, i + 1);

            if (count < imgEvent.size()) {
                if (imgEvent.get(count).GetNumber() == i) {
                    grid.add(imgEvent.get(count).GetImg(), 4, i + 1);
                    addImageClick(imgEvent.get(count));
                    count++;
                }
            }

        }

        btnNext = new Button();
        btnNext.setText("Next Day");

        btnPrevious = new Button();
        btnPrevious.setText("Previous Day");

        Label lblToday = new Label(currentDay.toString());

        grid.add(lblToday, 3, 0);
        grid.add(btnNext, 6, 7);
        grid.add(btnPrevious, 2, 7);

        return grid;
    }

    private int ParseLabel(Label lbl) {
        String sbjInfo = lbl.getText();
        String substring = sbjInfo.substring(0, 1);
        int number = Integer.parseInt(substring);
        return number;
    }

    private void addTranslateListener(final Label node) {
        node.setOnMousePressed(new EventHandler() {
            @Override
            public void handle(Event event) {
                int number = ParseLabel(node);
                frmAddSbj.SetSubject(DataProcessor.GetSubjectByNumber(currentSbj, number));
                try {
                    frmAddSbj.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(RS_Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        node.setOnMouseMoved(new EventHandler() {
            @Override
            public void handle(Event event) {
                node.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
            }
        });

        node.setOnMouseExited(new EventHandler() {
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
